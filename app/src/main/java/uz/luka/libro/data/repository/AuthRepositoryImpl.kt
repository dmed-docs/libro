package uz.luka.libro.data.repository

import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.OtpType
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.auth.user.UserInfo
import uz.luka.libro.domain.model.AuthResult
import uz.luka.libro.domain.model.User
import uz.luka.libro.domain.repository.AuthRepository
import javax.inject.Inject
import kotlin.time.ExperimentalTime

class AuthRepositoryImpl @Inject constructor(
    private val auth: Auth
) : AuthRepository {
    
    override suspend fun signUpWithEmail(email: String, password: String): AuthResult<User> {
        return try {
            // Supabase da signUp avtomatik email yuboradi
            auth.signUpWith(Email) {
                this.email = email
                this.password = password
            }
            
            // Email yuborildi, foydalanuvchi email ni tasdiqlashi kerak
            AuthResult.Success(
                User(
                    id = "",
                    email = email,
                    phone = null,
                    fullName = null
                )
            )
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "Ro'yxatdan o'tishda xatolik")
        }
    }
    
    override suspend fun signInWithEmail(email: String, password: String): AuthResult<User> {
        return try {
            auth.signInWith(Email) {
                this.email = email
                this.password = password
            }
            
            val userInfo = auth.currentUserOrNull()
            if (userInfo != null) {
                AuthResult.Success(userInfo.toUser())
            } else {
                AuthResult.Error("Foydalanuvchi topilmadi")
            }
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "Login qilishda xatolik")
        }
    }
    
    override suspend fun signUpWithPhone(phone: String): AuthResult<String> {
        return try {
            // TODO: Telefon bilan ro'yxatdan o'tish keyinroq qo'shiladi
            AuthResult.Error("Telefon bilan ro'yxatdan o'tish hozircha mavjud emas")
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "Xatolik")
        }
    }
    
    override suspend fun verifyOtp(email: String, token: String): AuthResult<User> {
        return try {
            auth.verifyEmailOtp(
                type = OtpType.Email.EMAIL,
                email = email,
                token = token
            )
            
            val userInfo = auth.currentUserOrNull()
            if (userInfo != null) {
                AuthResult.Success(userInfo.toUser())
            } else {
                AuthResult.Error("Foydalanuvchi topilmadi")
            }
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "Kod noto'g'ri")
        }
    }
    
    override suspend fun signOut(): AuthResult<Unit> {
        return try {
            auth.signOut()
            AuthResult.Success(Unit)
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "Chiqishda xatolik")
        }
    }
    
    override suspend fun getCurrentUser(): AuthResult<User?> {
        return try {
            val userInfo = auth.currentUserOrNull()
            AuthResult.Success(userInfo?.toUser())
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "Xatolik")
        }
    }
    
    override suspend fun resetPassword(email: String): AuthResult<Unit> {
        return try {
            auth.resetPasswordForEmail(email)
            AuthResult.Success(Unit)
        } catch (e: Exception) {
            AuthResult.Error(e.message ?: "Parolni tiklashda xatolik")
        }
    }
    
    // Helper function
    @OptIn(ExperimentalTime::class)
    private fun UserInfo.toUser(): User {
        return User(
            id = this.id,
            email = this.email,
            phone = this.phone,
            fullName = this.userMetadata?.get("full_name") as? String,
            avatarUrl = this.userMetadata?.get("avatar_url") as? String,
            createdAt = this.createdAt.toString()
        )
    }
}
