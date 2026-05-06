# Database O'zgarishlar - Qisqacha

## ✅ Bajarilgan ishlar:

### 1. UserProfile modeli yangilandi
**Yangi fieldlar qo'shildi:**
- `username` - Foydalanuvchi nomi (@xojiakbar_2329)
- `phone` - Telefon raqami
- `websiteUrl` - Veb-sayt
- `location` - Joylashuv
- `gender` - Jinsi
- `readsCount` - O'qilgan kitoblar soni
- `followingCount` - Kuzatilayotganlar soni
- `followersCount` - Kuzatuvchilar soni
- `isVerified` - Tasdiqlangan akkaunt
- `isPrivate` - Yopiq akkaunt
- `termsAcceptedAt` - Shartlarni qabul qilgan vaqti
- `lastLoginAt` - Oxirgi kirish vaqti

### 2. Yangi modellar yaratildi:
- ✅ `Follow.kt` - Kuzatish uchun
- ✅ `SavedBook.kt` - Saqlangan kitoblar uchun
- ✅ `ReadingHistory.kt` - O'qish tarixi uchun

### 3. Dokumentatsiya yaratildi:
- ✅ `SUPABASE_PROFILE_UPDATE.md` - To'liq SQL migration script

## 📋 Keyingi qadamlar:

### Supabase da bajarish kerak:

1. **SQL Editor** ga o'ting
2. `SUPABASE_PROFILE_UPDATE.md` faylidagi SQL scriptlarni ketma-ket bajaring:
   - ✅ Ustunlar qo'shish (1-bo'lim)
   - ✅ Indexlar yaratish (2-bo'lim)
   - ✅ Triggerlar o'rnatish (3-bo'lim)
   - ✅ Mavjud ma'lumotlarni yangilash (4-bo'lim)
   - ⚠️ RLS sozlash (6-bo'lim) - Agar kerak bo'lsa
   - ⚠️ Qo'shimcha jadvallar (8-bo'lim) - Keyinchalik

### Kotlin kodda bajarish kerak:

1. **UserProfileRepository** ni yangilash:
   - `updateProfile()` funksiyasiga yangi fieldlarni qo'shish
   - `getProfileByUsername()` funksiyasini qo'shish
   - Follow/Unfollow funksiyalarini qo'shish

2. **ProfileViewModel** yaratish:
   - Profil ma'lumotlarini olish
   - Profil yangilash
   - Statistikani ko'rsatish

3. **EditProfileScreen** ni to'ldirish:
   - Username o'zgartirish
   - Bio, location, website qo'shish
   - Avatar yuklash

## 🎯 Hozirgi vazifa:

Siz **ShareProfileScreen** ustida ishlayotgan edingiz. Database o'zgarishlari bajarilgandan keyin:

1. ProfileScreen da real ma'lumotlarni ko'rsatish (ViewModel orqali)
2. ShareProfileScreen da username va fullName ni real ma'lumotlardan olish
3. QR kod generatsiya qilish funksiyasini qo'shish

## 📝 Eslatma:

- Password ni hech qachon plain text saqlamang!
- Phone raqamni international format da saqlang: `+998901234567`
- Username faqat lowercase, raqamlar va underscore bo'lishi kerak
- Avatar URL ni Supabase Storage dan oling
