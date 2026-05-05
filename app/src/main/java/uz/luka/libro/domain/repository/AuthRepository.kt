package uz.luka.libro.domain.repository

import uz.luka.libro.domain.model.AuthResult
import uz.luka.libro.domain.model.User

interface AuthRepository {
    
    // Email bilan ro'yxatdan o'tish
    suspend fun signUpWithEmail(email: String, password: String): AuthResult<User>
    
    // Email bilan login
    suspend fun signInWithEmail(email: String, password: String): AuthResult<User>
    
    // Telefon bilan ro'yxatdan o'tish (keyinroq)
    suspend fun signUpWithPhone(phone: String): AuthResult<String>
    
    // Verification code tekshirish
    suspend fun verifyOtp(email: String, token: String): AuthResult<User>
    
    // Logout
    suspend fun signOut(): AuthResult<Unit>
    
    // Joriy foydalanuvchi
    suspend fun getCurrentUser(): AuthResult<User?>
    
    // Parolni tiklash
    suspend fun resetPassword(email: String): AuthResult<Unit>
}
