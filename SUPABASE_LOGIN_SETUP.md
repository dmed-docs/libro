# Supabase Login Setup

## 1. Email ustuni qo'shish

SQL Editor ga o'ting va bajaring:

```sql
-- user_profiles jadvaliga email ustuni qo'shish
ALTER TABLE user_profiles 
ADD COLUMN email TEXT UNIQUE;

-- Mavjud ma'lumotlarga email qo'shish (test uchun)
-- Agar bazada ma'lumot bo'lsa, ularni yangilang
UPDATE user_profiles 
SET email = full_name || '@test.com' 
WHERE email IS NULL;
```

## 2. Login funksiyasini test qilish

### SignUp qiling:
1. Email: `test@gmail.com`
2. Password: `123456`
3. Birthdate: `2000-01-01`
4. Full Name: `Test User`

### Login qiling:
1. Email: `test@gmail.com` (yoki signup da kiritgan email)
2. Password: `123456` (yoki signup da kiritgan password)

## 3. Logcat da ko'rasiz:

```
🔵 LIBRO: Login urinishi - email: test@gmail.com
✅ LIBRO: Login muvaffaqiyatli - User: Test User
```

## 4. Xato bo'lsa:

```
❌ LIBRO: User topilmadi
yoki
❌ LIBRO: Parol noto'g'ri
```

## Eslatma:

Hozir email ni `full_name` bilan solishtirish qilinmoqda (test uchun).
Email ustuni qo'shilgandan keyin, email bilan solishtirish ishlaydi.
