package uz.luka.libro.domain.repository

import uz.luka.libro.domain.model.AuthResult
import uz.luka.libro.domain.model.UserProfile

interface UserProfileRepository {
    
    suspend fun createProfile(
        userId: String,
        username: String,
        birthdate: String,
        password: String
    ): AuthResult<UserProfile>
    
    suspend fun getProfile(userId: String): AuthResult<UserProfile?>
    
    suspend fun updateProfile(
        userId: String,
        username: String? = null,
        birthdate: String? = null,
        avatarUrl: String? = null,
        bio: String? = null,
        location: String? = null,
        gender: String? = null,
        websiteUrl: String? = null
    ): AuthResult<UserProfile>
    
    // Login funksiyasi
    suspend fun login(
        email: String,
        password: String
    ): AuthResult<UserProfile>
    
    // Email mavjudligini tekshirish
    suspend fun checkEmailExists(email: String): AuthResult<Boolean>
    
    // Username mavjudligini tekshirish
    suspend fun checkUsernameExists(username: String): AuthResult<Boolean>
    
    // Phone mavjudligini tekshirish
    suspend fun checkPhoneExists(phone: String): AuthResult<Boolean>
}
