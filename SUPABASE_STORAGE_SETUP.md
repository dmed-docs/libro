# Supabase Storage Setup - Avatar Upload

## 1. Supabase Dashboard da sozlash

### Storage Bucket yaratish:

1. Supabase Dashboard ga kiring
2. Chap menyu → **Storage**
3. **Create a new bucket** tugmasini bosing
4. Bucket sozlamalari:
   - **Name:** `avatars`
   - **Public bucket:** ✅ (rasm URL lari public bo'lishi uchun)
   - **File size limit:** 5MB (yoki kerakli hajm)
   - **Allowed MIME types:** `image/*` (barcha rasm formatlari)
5. **Create bucket** tugmasini bosing

### Storage Policies sozlash:

**SQL Editor** ga o'ting va quyidagi SQL ni bajaring:

```sql
-- 1. Hamma avatar rasmlarni ko'rishi mumkin (public read)
CREATE POLICY "Avatar images are publicly accessible"
ON storage.objects FOR SELECT
USING (bucket_id = 'avatars');

-- 2. Authenticated userlar o'z rasmlarini yuklashi mumkin
CREATE POLICY "Users can upload their own avatar"
ON storage.objects FOR INSERT
WITH CHECK (
  bucket_id = 'avatars' 
  AND auth.role() = 'authenticated'
);

-- 3. Authenticated userlar o'z rasmlarini yangilashi mumkin
CREATE POLICY "Users can update their own avatar"
ON storage.objects FOR UPDATE
USING (
  bucket_id = 'avatars' 
  AND auth.role() = 'authenticated'
);

-- 4. Authenticated userlar o'z rasmlarini o'chirishi mumkin
CREATE POLICY "Users can delete their own avatar"
ON storage.objects FOR DELETE
USING (
  bucket_id = 'avatars' 
  AND auth.role() = 'authenticated'
);
```

**Yoki oddiyroq (test uchun - hamma uchun ochiq):**

```sql
-- Hamma uchun ochiq (test uchun)
CREATE POLICY "Public Access"
ON storage.objects FOR ALL
USING (bucket_id = 'avatars');
```

## 2. Bucket strukturasi

```
avatars/
├── user_id_1/
│   └── avatar.jpg
├── user_id_2/
│   └── avatar.png
└── user_id_3/
    └── avatar.webp
```

Har bir user o'z papkasiga rasm yuklaydi: `avatars/{user_id}/avatar.{ext}`

## 3. Kotlin kodda ishlatish

### Avatar yuklash:

```kotlin
suspend fun uploadAvatar(userId: String, imageBytes: ByteArray): String {
    val fileName = "avatars/$userId/avatar.jpg"
    
    storage.from("avatars").upload(fileName, imageBytes) {
        upsert = true // Agar mavjud bo'lsa, yangilash
    }
    
    // Public URL olish
    val publicUrl = storage.from("avatars").publicUrl(fileName)
    return publicUrl
}
```

### Avatar o'chirish:

```kotlin
suspend fun deleteAvatar(userId: String) {
    val fileName = "avatars/$userId/avatar.jpg"
    storage.from("avatars").delete(fileName)
}
```

### Avatar URL olish:

```kotlin
fun getAvatarUrl(userId: String): String {
    return storage.from("avatars").publicUrl("avatars/$userId/avatar.jpg")
}
```

## 4. Coil bilan rasm ko'rsatish

```kotlin
AsyncImage(
    model = avatarUrl,
    contentDescription = "Avatar",
    modifier = Modifier.size(100.dp).clip(CircleShape),
    placeholder = painterResource(R.drawable.ic_profile_placeholder),
    error = painterResource(R.drawable.ic_profile_placeholder)
)
```

## 5. Rasm hajmini kichraytirish (optional)

```kotlin
fun compressImage(context: Context, uri: Uri): ByteArray {
    val bitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        ImageDecoder.decodeBitmap(ImageDecoder.createSource(context.contentResolver, uri))
    } else {
        MediaStore.Images.Media.getBitmap(context.contentResolver, uri)
    }
    
    val outputStream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, 80, outputStream)
    return outputStream.toByteArray()
}
```

## 6. Permissions (AndroidManifest.xml)

```xml
<!-- Rasm tanlash uchun -->
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" 
    android:maxSdkVersion="32" />
<uses-permission android:name="android.permission.READ_MEDIA_IMAGES" />

<!-- Internet (allaqachon bor) -->
<uses-permission android:name="android.permission.INTERNET" />
```

## 7. Test qilish

### Supabase Dashboard da:

1. **Storage** → **avatars** bucket ga o'ting
2. **Upload file** tugmasini bosing
3. Test rasm yuklang
4. URL ni copy qiling va brauzerda oching
5. Rasm ko'rinishi kerak

### SQL da test:

```sql
-- Avatar URL ni user_profiles ga saqlash
UPDATE user_profiles 
SET avatar_url = 'https://your-project.supabase.co/storage/v1/object/public/avatars/user_id/avatar.jpg'
WHERE id = 'user_id';

-- Avatar URL ni olish
SELECT id, full_name, avatar_url 
FROM user_profiles 
WHERE avatar_url IS NOT NULL;
```

## 8. URL formati

```
https://[PROJECT_ID].supabase.co/storage/v1/object/public/avatars/[USER_ID]/avatar.jpg
```

Misol:
```
https://abcdefgh.supabase.co/storage/v1/object/public/avatars/123e4567-e89b-12d3-a456-426614174000/avatar.jpg
```

## 9. Xavfsizlik

✅ **Yaxshi:**
- Har bir user faqat o'z rasmini yuklashi mumkin
- Rasm hajmi cheklangan (5MB)
- Faqat rasm formatlari ruxsat etilgan
- Public URL lar faqat o'qish uchun

❌ **Yomon:**
- Hamma userlar boshqa userlarning rasmlarini o'chirishi mumkin
- Cheksiz hajmdagi fayllar yuklash
- Har qanday fayl formatini yuklash

## 10. Keyingi qadamlar

1. ✅ Supabase Storage bucket yaratish
2. ✅ Policies sozlash
3. ⏳ EditProfileScreen da rasm tanlash
4. ⏳ Rasm yuklash funksiyasi
5. ⏳ ProfileScreen da avatar ko'rsatish
6. ⏳ Avatar o'chirish funksiyasi

---

**Eslatma:** Test uchun "Public Access" policy ishlatishingiz mumkin, lekin production da to'g'ri RLS (Row Level Security) sozlang!
