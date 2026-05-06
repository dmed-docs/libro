package uz.luka.libro.data.repository

import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.query.Columns
import uz.luka.libro.domain.model.AuthResult
import uz.luka.libro.domain.model.UserProfile
import uz.luka.libro.domain.repository.UserProfileRepository
import javax.inject.Inject
import kotlin.compareTo
import kotlin.text.get

class UserProfileRepositoryImpl @Inject constructor(
    private val postgrest: Postgrest
) : UserProfileRepository {
    
    override suspend fun createProfile(
        userId: String,
        username: String,
        birthdate: String,
        password: String
    ): AuthResult<UserProfile> {
        return try {
            // Map sifatida yuborish - serialization muammosini hal qiladi
            val profileData = mapOf(
                "username" to username,
                "birthdate" to birthdate,
                "password" to password,
                "email" to userId.lowercase() // Email lowercase
            )
            
            println("🔵 LIBRO: Bazaga yuborilayotgan ma'lumot: ${profileData.filterKeys { it != "password" }}")
            
            // Insert qilish va yaratilgan profilni olish
            val insertedProfile = postgrest["user_profiles"]
                .insert(profileData) {
                    select()
                }
                .decodeSingle<UserProfile>()
            
            println("✅ LIBRO: Insert muvaffaqiyatli! ID: ${insertedProfile.id}")
            
            AuthResult.Success(insertedProfile)
        } catch (e: Exception) {
            println("❌ LIBRO: Insert xatosi: ${e.message}")
            e.printStackTrace()
            AuthResult.Error(e.message ?: "Profil yaratishda xatolik")
        }
    }
    
    override suspend fun getProfile(userId: String): AuthResult<UserProfile?> {
        return try {
            val profile = postgrest["user_profiles"]
                .select(columns = Columns.ALL) {
                    filter {
                        eq("id", userId)
                    }
                }
                .decodeSingleOrNull<UserProfile>()
            
            AuthResult.Success(profile)
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "Profil olishda xatolik")
        }
    }
    
    override suspend fun updateProfile(
        userId: String,
        username: String?,
        birthdate: String?,
        avatarUrl: String?,
        bio: String?,
        location: String?,
        gender: String?,
        websiteUrl: String?
    ): AuthResult<UserProfile> {
        return try {
            val updates = buildMap<String, String> {
                username?.let { put("username", it) }
                birthdate?.let { put("birthdate", it) }
                avatarUrl?.let { put("avatar_url", it) }
                bio?.let { put("bio", it) }
                location?.let { put("location", it) }
                gender?.let { put("gender", it) }
                websiteUrl?.let { put("website_url", it) }
            }
            
            if (updates.isEmpty()) {
                return AuthResult.Error("Hech narsa o'zgartirilmadi")
            }
            
            println("🔵 LIBRO: Profil yangilanmoqda - updates: $updates")
            
            postgrest["user_profiles"].update(updates) {
                filter {
                    eq("id", userId)
                }
            }
            
            // Yangilangan profilni olish
            val profile = postgrest["user_profiles"]
                .select(columns = Columns.ALL) {
                    filter {
                        eq("id", userId)
                    }
                }
                .decodeSingle<UserProfile>()
            
            println("✅ LIBRO: Profil yangilandi")
            
            AuthResult.Success(profile)
        } catch (e: Exception) {
            println("❌ LIBRO: Profil yangilashda xato: ${e.message}")
            e.printStackTrace()
            AuthResult.Error(e.message ?: "Profil yangilashda xatolik")
        }
    }
    
    override suspend fun login(
        email: String,
        password: String
    ): AuthResult<UserProfile> {
        return try {
            val input = email.trim().lowercase()
            println("🔵 LIBRO: Login urinishi - input: $input")
            println("🔵 LIBRO: Password: ${password.take(3)}***")
            
            // Email yoki username bilan qidirish
            val passwordCheck = postgrest["user_profiles"]
                .select(columns = Columns.list("id", "password", "email", "username")) {
                    filter {
                        or {
                            ilike("email", input)
                            eq("username", input)
                        }
                    }
                }
                .decodeSingleOrNull<Map<String, String?>>()
            
            println("🔵 LIBRO: Bazadan kelgan ma'lumot: $passwordCheck")
            
            if (passwordCheck == null) {
                println("❌ LIBRO: User topilmadi - bazada bunday email/username yo'q")
                return AuthResult.Error("Email/Username yoki parol noto'g'ri")
            }
            
            val savedPassword = passwordCheck["password"]
            val foundEmail = passwordCheck["email"]
            val foundUsername = passwordCheck["username"]
            
            println("🔵 LIBRO: Topilgan email: $foundEmail")
            println("🔵 LIBRO: Topilgan username: $foundUsername")
            println("🔵 LIBRO: Saqlangan parol: ${savedPassword?.take(3)}***")
            println("🔵 LIBRO: Kiritilgan parol: ${password.take(3)}***")
            
            if (savedPassword != password) {
                println("❌ LIBRO: Parol noto'g'ri - mos kelmadi")
                return AuthResult.Error("Email/Username yoki parol noto'g'ri")
            }
            
            val userId = passwordCheck["id"] ?: ""
            println("🔵 LIBRO: User ID: $userId")
            
            // Password to'g'ri bo'lsa, user profilini olish (password siz)
            val profile = postgrest["user_profiles"]
                .select(columns = Columns.ALL) {
                    filter {
                        eq("id", userId)
                    }
                }
                .decodeSingle<UserProfile>()
            
            println("✅ LIBRO: Login muvaffaqiyatli! User: ${profile.username}")
            AuthResult.Success(profile)
        } catch (e: Exception) {
            println("❌ LIBRO: Login xatosi: ${e.message}")
            e.printStackTrace()
            AuthResult.Error(e.message ?: "Login xatosi")
        }
    }


    override suspend fun checkEmailExists(email: String): AuthResult<Boolean> {
        return try {
            val emailLower = email.trim().lowercase()
            
            val allProfiles = postgrest["user_profiles"]
                .select(columns = Columns.list("id", "email")) {
                    // Filter yo'q - hammasini olish
                }
                .decodeList<Map<String, String?>>()
            
            val exists = allProfiles.any { profile ->
                val dbEmail = profile["email"]?.trim()?.lowercase()
                dbEmail == emailLower
            }
            
            AuthResult.Success(exists)
        } catch (e: Exception) {
            println("❌ LIBRO: Email tekshirish xatosi: ${e.message}")
            e.printStackTrace()
            AuthResult.Error(e.message ?: "Email tekshirish xatosi")
        }
    }

    override suspend fun checkUsernameExists(username: String): AuthResult<Boolean> {
        return try {
            val usernameTrimmed = username.trim()
            
            val count = postgrest["user_profiles"]
                .select(columns = Columns.list("id")) {
                    filter {
                        eq("username", usernameTrimmed)
                    }
                }
                .decodeList<Map<String, String>>()
                .size
            
            val exists = count > 0
            
            AuthResult.Success(exists)
        } catch (e: Exception) {
            println("❌ LIBRO: Username tekshirish xatosi: ${e.message}")
            e.printStackTrace()
            AuthResult.Error(e.message ?: "Username tekshirish xatosi")
        }
    }


    override suspend fun checkPhoneExists(phone: String): AuthResult<Boolean> {
        return try {
            val phoneTrimmed = phone.trim()

            val count = postgrest["user_profiles"]
                .select(columns = Columns.list("id")) {
                    filter {
                        eq("phone", phoneTrimmed)
                    }
                }
                .decodeList<Map<String, String>>()
                .size

            val exists = count > 0

            AuthResult.Success(exists)
        } catch (e: Exception) {
            println("❌ LIBRO: Phone tekshirish xatosi: ${e.message}")
            e.printStackTrace()
            AuthResult.Error(e.message ?: "Phone tekshirish xatosi")
        }
    }
}

    




