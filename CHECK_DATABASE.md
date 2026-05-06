# Bazani Tekshirish

## Supabase SQL Editor da bajaring:

```sql
-- 1. Barcha emaillarni ko'rish
SELECT id, email, username, created_at 
FROM user_profiles 
ORDER BY created_at DESC;

-- 2. Aniq email bilan qidirish
SELECT id, email, username 
FROM user_profiles 
WHERE email = 'cs2naziluka@gmail.com';

-- 3. Email lowercase bilan qidirish
SELECT id, email, username 
FROM user_profiles 
WHERE LOWER(email) = 'cs2naziluka@gmail.com';

-- 4. Email LIKE bilan qidirish (case-insensitive)
SELECT id, email, username 
FROM user_profiles 
WHERE email ILIKE '%cs2naziluka%';
```

## Natijalarni yuboring

Yuqoridagi SQL querylarni Supabase SQL Editor da bajaring va natijalarni menga yuboring. Shunda bazada email qanday formatda saqlanganini ko'ramiz.
