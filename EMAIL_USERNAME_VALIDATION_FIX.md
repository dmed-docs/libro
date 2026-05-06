# Email va Username Validatsiya Tuzatishlari

## Muammo
1. **Registratsiya paytida email tekshirilmayotgan edi** - Mavjud emailni kiritganda ham ro'yxatdan o'tkazib yuborayotgan edi
2. **Edit Profile da username validatsiyasi ishlamayotgan edi** - Mavjud usernamega o'zgartirganda ham saqlab qo'yayotgan edi

## Tuzatilgan Fayllar

### 1. UserProfileRepositoryImpl.kt
**O'zgarish**: `checkEmailOrUsernameExists()` funksiyasidagi when shartlari tartibini va logikasini tuzatdim

**Sabab**: 
- Avvalgi kodda when shartlari noto'g'ri tartibda edi
- Email tekshirish uchun `email.isEmpty()` sharti ishlatilgan edi, lekin email har doim beriladi

**Yangi logika**:
```kotlin
when {
    // 1. Email VA username ikkalasi ham berilgan
    username != null && email.isNotEmpty() -> {
        // OR sharti bilan ikkalasini ham tekshirish
    }
    // 2. Faqat username tekshirish
    username != null -> {
        // Faqat username
    }
    // 3. Faqat email tekshirish
    email.isNotEmpty() -> {
        // Faqat email
    }
    else -> 0
}
```

**Qo'shimcha**:
- Har bir branch uchun debug log qo'shdim
- Email lowercase qilinadi (case-insensitive qidiruv)

### 2. SignUpWithEmailViewModel.kt
**O'zgarish**: Email validatsiya logikasiga batafsil logging qo'shdim

**Qo'shilgan**:
- Email trim() qilish (bo'sh joylarni olib tashlash)
- Har bir qadamda debug log
- Natijani aniq ko'rsatish

**Logging**:
```kotlin
println("🔵 LIBRO: SignUp - Email kiritildi: '$email'")
println("🔵 LIBRO: Email mavjudligini tekshirish boshlandi...")
println("🔵 LIBRO: Tekshirish natijasi: ${result.data}")
```

### 3. EditProfileViewModel.kt
**O'zgarish**: Username validatsiya logikasini yaxshiladim

**Yangi xususiyatlar**:
- Username trim() qilish
- Bo'sh username tekshirish
- Username o'zgarganligini aniq tekshirish
- Batafsil logging

**Logika**:
```kotlin
val currentUsername = userSession.username
val newUsername = state.username.trim()

// 1. Bo'sh username tekshirish
if (newUsername.isEmpty()) {
    return // Xato ko'rsatish
}

// 2. O'zgargan bo'lsa tekshirish
if (currentUsername != newUsername) {
    // Username mavjudligini tekshirish
    checkEmailOrUsernameExists(email = "", username = newUsername)
}
```

## Test Qilish Kerak

### Email Validatsiya (Registratsiya)
1. ✅ Yangi email bilan registratsiya - ishlashi kerak
2. ✅ Mavjud email bilan registratsiya - "Bu email allaqachon ro'yxatdan o'tgan" xatosi
3. ✅ Noto'g'ri format email - "Email noto'g'ri formatda" xatosi

### Username Validatsiya (Edit Profile)
1. ✅ Username o'zgartirmasdan saqlash - ishlashi kerak
2. ✅ Yangi (mavjud bo'lmagan) usernamega o'zgartirish - ishlashi kerak
3. ✅ Mavjud usernamega o'zgartirish - "Bu username allaqachon band" xatosi
4. ✅ Bo'sh username - "Username bo'sh bo'lishi mumkin emas" xatosi

## Logcat da Ko'rish Kerak

### Email tekshirish (Registratsiya)
```
🔵 LIBRO: SignUp - Email kiritildi: 'test@example.com'
🔵 LIBRO: Email mavjudligini tekshirish boshlandi...
🔵 LIBRO: Email/Username tekshirilmoqda - email: 'test@example.com', username: 'null'
🔵 LIBRO: Faqat email tekshirilmoqda: test@example.com
🔵 LIBRO: Natija - Mavjudmi? true (count: 1)
🔵 LIBRO: Tekshirish natijasi: true
❌ LIBRO: Email allaqachon ro'yxatdan o'tgan: test@example.com
```

### Username tekshirish (Edit Profile)
```
🔵 LIBRO: Username o'zgargan, tekshirilmoqda: 'asadbek' -> 'asadbek1'
🔵 LIBRO: Email/Username tekshirilmoqda - email: '', username: 'asadbek1'
🔵 LIBRO: Faqat username tekshirilmoqda: asadbek1
🔵 LIBRO: Natija - Mavjudmi? true (count: 1)
❌ LIBRO: Username allaqachon band: asadbek1
```

## Keyingi Qadamlar

1. **Test qilish**: Yuqoridagi barcha holatlarni test qiling
2. **Logcat ni kuzatish**: Har bir test paytida logcat da to'g'ri xabarlar chiqayotganini tekshiring
3. **Agar ishlamasa**: Logcat dan xato xabarlarini yuboring

## Eslatma

- Email har doim **lowercase** saqlanadi va tekshiriladi
- Username **as-is** saqlanadi (lowercase yoki underscore qo'shilmaydi)
- Trim() funksiyasi bo'sh joylarni olib tashlaydi
