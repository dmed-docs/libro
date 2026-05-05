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
        fullName: String,
        birthdate: String,
        password: String
    ): AuthResult<UserProfile> {
        return try {
            // Map sifatida yuborish - serialization muammosini hal qiladi
            val profileData = mapOf(
                "full_name" to fullName,
                "birthdate" to birthdate,
                "password" to password, // TEST UCHUN - keyinchalik auth.users ga o'tadi
                "email" to userId // userId o'rniga email yuboramiz
            )
            
            println("🔵 LIBRO: Bazaga yuborilayotgan ma'lumot: ${profileData.filterKeys { it != "password" }}")
            
            postgrest["user_profiles"].insert(profileData)
            
            println("✅ LIBRO: Insert muvaffaqiyatli!")
            
            // Yaratilgan profilni qaytarish
            val profile = UserProfile(
                id = null,
                fullName = fullName,
                birthdate = birthdate
            )
            
            AuthResult.Success(profile)
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
        fullName: String?,
        birthdate: String?,
        avatarUrl: String?,
        bio: String?
    ): AuthResult<UserProfile> {
        return try {
            val updates = mutableMapOf<String, Any>()
            fullName?.let { updates["full_name"] = it }
            birthdate?.let { updates["birthdate"] = it }
            avatarUrl?.let { updates["avatar_url"] = it }
            bio?.let { updates["bio"] = it }
            
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
            
            AuthResult.Success(profile)
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "Profil yangilashda xatolik")
        }
    }
    
    override suspend fun login(
        email: String,
        password: String
    ): AuthResult<UserProfile> {
        return try {
            println("🔵 LIBRO: Login urinishi - input: $email")
            println("🔵 LIBRO: Password: ${password.take(3)}***")
            
            // Avval password tekshirish uchun faqat password ni olish
            val passwordCheck = postgrest["user_profiles"]
                .select(columns = Columns.list("id", "password", "email", "full_name")) {
                    filter {
                        or {
                            eq("email", email)
                            eq("full_name", email)
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
                .select(columns = Columns.list("id", "full_name", "birthdate", "email", "avatar_url", "bio", "created_at", "updated_at")) {
                    filter {
                        eq("id", userId)
                    }
                }
                .decodeSingle<UserProfile>()
            
            println("✅ LIBRO: Login muvaffaqiyatli! User: ${profile.fullName}")
            AuthResult.Success(profile)
        } catch (e: Exception) {
            println("❌ LIBRO: Login xatosi: ${e.message}")
            e.printStackTrace()
            AuthResult.Error(e.message ?: "Login xatosi")
        }
    }


    override suspend fun checkEmailOrUsernameExists(
        email: String,
        username: String?
    ): AuthResult<Boolean> {
        return try {
            println("🔵 LIBRO: Email/Username tekshirilmoqda - email: $email, username: $username")

            // Email yoki username mavjudligini tekshirish
            val count = if (username != null) {
                postgrest["user_profiles"]
                    .select(columns = Columns.list("id")) {
                        filter {
                            or {
                                eq("email", email)
                                eq("full_name", username)
                            }
                        }
                    }
                    .decodeList<Map<String, String>>()
                    .size
            } else {
                postgrest["user_profiles"]
                    .select(columns = Columns.list("id")) {
                        filter {
                            eq("email", email)
                        }
                    }
                    .decodeList<Map<String, String>>()
                    .size
            }

            val exists = count > 0
            println("🔵 LIBRO: Mavjudmi? $exists")

            AuthResult.Success(exists)
        } catch (e: Exception) {
            println("❌ LIBRO: Tekshirish xatosi: ${e.message}")
            e.printStackTrace()
            AuthResult.Error(e.message ?: "Tekshirish xatosi")
        }
    }


    override suspend fun checkPhoneExists(phone: String): AuthResult<Boolean> {
        return try {
            println("🔵 LIBRO: Phone tekshirilmoqda - phone: $phone")

            val count = postgrest["user_profiles"]
                .select(columns = Columns.list("id")) {
                    filter {
                        eq("phone", phone)
                    }
                }
                .decodeList<Map<String, String>>()
                .size

            val exists = count > 0
            println("🔵 LIBRO: Phone mavjudmi? $exists")

            AuthResult.Success(exists)
        } catch (e: Exception) {
            println("❌ LIBRO: Phone tekshirish xatosi: ${e.message}")
            e.printStackTrace()
            AuthResult.Error(e.message ?: "Tekshirish xatosi")
        }
    }
}

    

