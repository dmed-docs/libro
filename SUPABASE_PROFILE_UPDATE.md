# Supabase User Profiles O'zgarishlar

## 1. Yangi ustunlar qo'shish

SQL Editor ga o'ting va bajaring:

```sql
-- Username ustuni qo'shish (unique bo'lishi kerak)
ALTER TABLE user_profiles 
ADD COLUMN username TEXT UNIQUE;

-- Phone ustuni qo'shish (unique bo'lishi kerak)
ALTER TABLE user_profiles 
ADD COLUMN phone TEXT UNIQUE;

-- Password ustuni qo'shish (hashed password)
ALTER TABLE user_profiles 
ADD COLUMN password TEXT NOT NULL DEFAULT '';

-- Reads count (o'qilgan kitoblar soni)
ALTER TABLE user_profiles 
ADD COLUMN reads_count INTEGER DEFAULT 0;

-- Following count (kuzatilayotganlar soni)
ALTER TABLE user_profiles 
ADD COLUMN following_count INTEGER DEFAULT 0;

-- Followers count (kuzatuvchilar soni)
ALTER TABLE user_profiles 
ADD COLUMN followers_count INTEGER DEFAULT 0;

-- Is verified (tasdiqlangan akkaunt)
ALTER TABLE user_profiles 
ADD COLUMN is_verified BOOLEAN DEFAULT FALSE;

-- Is private (yopiq akkaunt)
ALTER TABLE user_profiles 
ADD COLUMN is_private BOOLEAN DEFAULT FALSE;

-- Website URL
ALTER TABLE user_profiles 
ADD COLUMN website_url TEXT;

-- Location
ALTER TABLE user_profiles 
ADD COLUMN location TEXT;

-- Gender
ALTER TABLE user_profiles 
ADD COLUMN gender TEXT;

-- Terms accepted at (shartlarni qabul qilgan vaqti)
ALTER TABLE user_profiles 
ADD COLUMN terms_accepted_at TIMESTAMP;

-- Last login at
ALTER TABLE user_profiles 
ADD COLUMN last_login_at TIMESTAMP;
```

## 2. Indexlar qo'shish (tezlik uchun)

```sql
-- Username bo'yicha qidiruv tezlashtirish
CREATE INDEX idx_user_profiles_username ON user_profiles(username);

-- Email bo'yicha qidiruv tezlashtirish
CREATE INDEX idx_user_profiles_email ON user_profiles(email);

-- Phone bo'yicha qidiruv tezlashtirish
CREATE INDEX idx_user_profiles_phone ON user_profiles(phone);

-- Full name bo'yicha qidiruv (LIKE operatori uchun)
CREATE INDEX idx_user_profiles_full_name ON user_profiles(full_name);
```

## 3. Trigger qo'shish (updated_at avtomatik yangilanishi uchun)

```sql
-- Updated_at ni avtomatik yangilash funksiyasi
CREATE OR REPLACE FUNCTION update_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = NOW();
    RETURN NEW;
END;
$$ language 'plpgsql';

-- Trigger yaratish
CREATE TRIGGER update_user_profiles_updated_at 
    BEFORE UPDATE ON user_profiles 
    FOR EACH ROW 
    EXECUTE FUNCTION update_updated_at_column();
```

## 4. Mavjud ma'lumotlarni yangilash (agar kerak bo'lsa)

```sql
-- Mavjud foydalanuvchilarga username berish (email dan olish)
UPDATE user_profiles 
SET username = LOWER(SPLIT_PART(email, '@', 1))
WHERE username IS NULL AND email IS NOT NULL;

-- Terms accepted at ni hozirgi vaqtga o'rnatish
UPDATE user_profiles 
SET terms_accepted_at = created_at
WHERE terms_accepted_at IS NULL;
```

## 5. Yangilangan jadval strukturasi

```sql
CREATE TABLE user_profiles (
  -- Asosiy ma'lumotlar
  id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  username TEXT UNIQUE,
  email TEXT UNIQUE,
  phone TEXT UNIQUE,
  password TEXT NOT NULL,
  
  -- Profil ma'lumotlari
  full_name TEXT NOT NULL,
  birthdate TEXT NOT NULL,
  bio TEXT,
  avatar_url TEXT,
  website_url TEXT,
  location TEXT,
  gender TEXT,
  
  -- Statistika
  reads_count INTEGER DEFAULT 0,
  following_count INTEGER DEFAULT 0,
  followers_count INTEGER DEFAULT 0,
  
  -- Sozlamalar
  is_verified BOOLEAN DEFAULT FALSE,
  is_private BOOLEAN DEFAULT FALSE,
  
  -- Vaqt ma'lumotlari
  terms_accepted_at TIMESTAMP,
  last_login_at TIMESTAMP,
  created_at TIMESTAMP DEFAULT NOW(),
  updated_at TIMESTAMP DEFAULT NOW()
);
```

## 6. Row Level Security (RLS) sozlash

```sql
-- RLS ni yoqish
ALTER TABLE user_profiles ENABLE ROW LEVEL SECURITY;

-- Hamma o'z profilini ko'rishi mumkin
CREATE POLICY "Users can view own profile" 
ON user_profiles FOR SELECT 
USING (auth.uid() = id);

-- Hamma boshqa profillarni ko'rishi mumkin (public ma'lumotlar)
CREATE POLICY "Users can view other profiles" 
ON user_profiles FOR SELECT 
USING (true);

-- Faqat o'z profilini yangilashi mumkin
CREATE POLICY "Users can update own profile" 
ON user_profiles FOR UPDATE 
USING (auth.uid() = id);

-- Faqat o'z profilini o'chirishi mumkin
CREATE POLICY "Users can delete own profile" 
ON user_profiles FOR DELETE 
USING (auth.uid() = id);
```

## 7. Test qilish

```sql
-- Yangi foydalanuvchi qo'shish
INSERT INTO user_profiles (
  username, 
  email, 
  phone,
  password,
  full_name, 
  birthdate,
  bio
) VALUES (
  'xojiakbar_2329',
  'xojiakbar@gmail.com',
  '+998901234567',
  'hashed_password_here',
  'Xojiakbar',
  '2000-01-01',
  'Kitob o''qishni yaxshi ko''raman'
);

-- Profilni yangilash
UPDATE user_profiles 
SET 
  bio = 'Yangi bio',
  location = 'Tashkent, Uzbekistan',
  website_url = 'https://example.com'
WHERE username = 'xojiakbar_2329';

-- Statistikani yangilash
UPDATE user_profiles 
SET 
  reads_count = reads_count + 1,
  following_count = following_count + 1
WHERE username = 'xojiakbar_2329';
```

## 8. Qo'shimcha jadvallar (keyinchalik kerak bo'ladi)

### Follows jadvali (kuzatish uchun)
```sql
CREATE TABLE follows (
  id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  follower_id UUID REFERENCES user_profiles(id) ON DELETE CASCADE,
  following_id UUID REFERENCES user_profiles(id) ON DELETE CASCADE,
  created_at TIMESTAMP DEFAULT NOW(),
  UNIQUE(follower_id, following_id)
);

-- Indexlar
CREATE INDEX idx_follows_follower ON follows(follower_id);
CREATE INDEX idx_follows_following ON follows(following_id);
```

### Saved Books jadvali (saqlangan kitoblar uchun)
```sql
CREATE TABLE saved_books (
  id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  user_id UUID REFERENCES user_profiles(id) ON DELETE CASCADE,
  book_id UUID, -- Kitoblar jadvali yaratilganda REFERENCES qo'shiladi
  created_at TIMESTAMP DEFAULT NOW(),
  UNIQUE(user_id, book_id)
);

-- Index
CREATE INDEX idx_saved_books_user ON saved_books(user_id);
```

### Reading History jadvali (o'qish tarixi uchun)
```sql
CREATE TABLE reading_history (
  id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  user_id UUID REFERENCES user_profiles(id) ON DELETE CASCADE,
  book_id UUID, -- Kitoblar jadvali yaratilganda REFERENCES qo'shiladi
  last_page INTEGER DEFAULT 0,
  progress_percentage INTEGER DEFAULT 0,
  started_at TIMESTAMP DEFAULT NOW(),
  last_read_at TIMESTAMP DEFAULT NOW(),
  completed_at TIMESTAMP,
  UNIQUE(user_id, book_id)
);

-- Indexlar
CREATE INDEX idx_reading_history_user ON reading_history(user_id);
CREATE INDEX idx_reading_history_last_read ON reading_history(last_read_at DESC);
```

## 9. Eslatmalar

1. **Password** - Hech qachon plain text saqlamang! Bcrypt yoki Argon2 bilan hash qiling.
2. **Phone** - International format da saqlang: `+998901234567`
3. **Username** - Faqat lowercase, raqamlar va underscore ruxsat bering
4. **Avatar URL** - Supabase Storage dan URL saqlang
5. **Counts** - Trigger yoki function orqali avtomatik yangilang

## 10. Migration qilish tartibi

1. ✅ Avval barcha ustunlarni qo'shing
2. ✅ Indexlarni yarating
3. ✅ Triggerlarni o'rnating
4. ✅ Mavjud ma'lumotlarni yangilang
5. ✅ RLS ni sozlang
6. ✅ Test qiling
7. ✅ Kotlin modellarini yangilang
