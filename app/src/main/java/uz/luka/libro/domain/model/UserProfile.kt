package uz.luka.libro.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserProfile(
    val id: String? = null, // Nullable - database o'zi yaratadi
    @SerialName("full_name")
    val fullName: String,
    val birthdate: String,
    val email: String? = null,
    @SerialName("avatar_url")
    val avatarUrl: String? = null,
    val bio: String? = null,
    @SerialName("created_at")
    val createdAt: String? = null,
    @SerialName("updated_at")
    val updatedAt: String? = null
)
