package uz.luka.libro.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String,
    val email: String?,
    val phone: String?,
    val fullName: String? = null,
    val avatarUrl: String? = null,
    val createdAt: String? = null
)

@Serializable
data class AuthResponse(
    val user: User?,
    val accessToken: String?,
    val refreshToken: String?
)

sealed class AuthResult<out T> {
    data class Success<T>(val data: T) : AuthResult<T>()
    data class Error(val message: String) : AuthResult<Nothing>()
    data object Loading : AuthResult<Nothing>()
}
