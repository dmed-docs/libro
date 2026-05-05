package uz.luka.libro.domain.repository

import uz.luka.libro.domain.model.AuthResult
import uz.luka.libro.domain.model.UserProfile

interface UserProfileRepository {
    
    suspend fun createProfile(
        userId: String,
        fullName: String,
        birthdate: String,
        password: String
    ): AuthResult<UserProfile>
    
    suspend fun getProfile(userId: String): AuthResult<UserProfile?>
    
    suspend fun updateProfile(
        userId: String,
        fullName: String? = null,
        birthdate: String? = null,
        avatarUrl: String? = null,
        bio: String? = null
    ): AuthResult<UserProfile>
    
    // Login funksiyasi
    suspend fun login(
        email: String,
        password: String
    ): AuthResult<UserProfile>
    
    // Email yoki username mavjudligini tekshirish
    suspend fun checkEmailOrUsernameExists(
        email: String,
        username: String? = null
    ): AuthResult<Boolean>
    
    // Phone mavjudligini tekshirish
    suspend fun checkPhoneExists(phone: String): AuthResult<Boolean>
}
