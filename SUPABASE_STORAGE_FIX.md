# Supabase Storage RLS Policy Tuzatish

## Muammo
```
❌ new row violates row-level security policy
```

Bu xato Supabase Storage da `avatars` bucket uchun INSERT policy yo'qligini yoki noto'g'ri sozlanganligini bildiradi.

## Yechim

### 1. Supabase Dashboard ga kiring
- Storage -> Policies -> avatars bucket

### 2. Quyidagi SQL ni bajaring:

```sql
-- 1. Avvalgi policylarni o'chirish (agar bor bo'lsa)
DROP POLICY IF EXISTS "Allow public read access" ON storage.objects;
DROP POLICY IF EXISTS "Allow authenticated users to upload avatars" ON storage.objects;
DROP POLICY IF EXISTS "Allow users to update their own avatars" ON storage.objects;
DROP POLICY IF EXISTS "Allow users to delete their own avatars" ON storage.objects;

-- 2. Yangi policylar yaratish

-- Public read access (hamma o'qiy oladi)
CREATE POLICY "Allow public read access"
ON storage.objects FOR SELECT
USING (bucket_id = 'avatars');

-- Authenticated users can INSERT (login qilgan userlar yuklashi mumkin)
CREATE POLICY "Allow authenticated users to upload avatars"
ON storage.objects FOR INSERT
TO authenticated
WITH CHECK (bucket_id = 'avatars');

-- Users can UPDATE their own avatars
CREATE POLICY "Allow users to update their own avatars"
ON storage.objects FOR UPDATE
TO authenticated
USING (bucket_id = 'avatars' AND auth.uid()::text = (storage.foldername(name))[1])
WITH CHECK (bucket_id = 'avatars' AND auth.uid()::text = (storage.foldername(name))[1]);

-- Users can DELETE their own avatars
CREATE POLICY "Allow users to delete their own avatars"
ON storage.objects FOR DELETE
TO authenticated
USING (bucket_id = 'avatars' AND auth.uid()::text = (storage.foldername(name))[1]);
```

### 3. Agar yuqoridagi ishlamasa, HAMMA uchun ochiq qilish (faqat test uchun):

```sql
-- DIQQAT: Bu faqat test uchun! Production da ishlatmang!

-- Avvalgi policylarni o'chirish
DROP POLICY IF EXISTS "Allow public read access" ON storage.objects;
DROP POLICY IF EXISTS "Allow public upload" ON storage.objects;
DROP POLICY IF EXISTS "Allow public update" ON storage.objects;
DROP POLICY IF EXISTS "Allow public delete" ON storage.objects;

-- Public access (hamma qila oladi)
CREATE POLICY "Allow public read access"
ON storage.objects FOR SELECT
USING (bucket_id = 'avatars');

CREATE POLICY "Allow public upload"
ON storage.objects FOR INSERT
WITH CHECK (bucket_id = 'avatars');

CREATE POLICY "Allow public update"
ON storage.objects FOR UPDATE
USING (bucket_id = 'avatars')
WITH CHECK (bucket_id = 'avatars');

CREATE POLICY "Allow public delete"
ON storage.objects FOR DELETE
USING (bucket_id = 'avatars');
```

### 4. Bucket sozlamalarini tekshirish

Storage -> avatars bucket -> Settings:
- **Public bucket**: ✅ ON (yoqilgan bo'lishi kerak)
- **File size limit**: 5MB (yoki kerakli hajm)
- **Allowed MIME types**: image/jpeg, image/png, image/jpg

## Test qilish

SQL ni bajarganingizdan keyin:
1. Dasturni qayta ishga tushiring
2. Edit Profile ga o'ting
3. Rasm tanlang
4. Logcat da `✅ LIBRO: Rasm yuklandi` xabarini ko'rishingiz kerak

## Eslatma

- Agar authenticated policy ishlamasa, public policy ishlatamiz (test uchun)
- Keyinchalik authenticated policyga qaytishingiz mumkin
- Production da doim authenticated policy ishlatish kerak
