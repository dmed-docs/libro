package uz.luka.libro.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserProfile(
    // Asosiy ma'lumotlar
    val id: String? = null, // Nullable - database o'zi yaratadi
    val username: String, // Faqat username, full_name yo'q
    val email: String? = null,
    val phone: String? = null,
    
    // Profil ma'lumotlari
    val birthdate: String,
    val bio: String? = null,
    @SerialName("avatar_url")
    val avatarUrl: String? = null,
    @SerialName("website_url")
    val websiteUrl: String? = null,
    val location: String? = null,
    val gender: String? = null,
    
    // Statistika
    @SerialName("reads_count")
    val readsCount: Int = 0,
    @SerialName("following_count")
    val followingCount: Int = 0,
    @SerialName("followers_count")
    val followersCount: Int = 0,
    
    // Sozlamalar
    @SerialName("is_verified")
    val isVerified: Boolean = false,
    @SerialName("is_private")
    val isPrivate: Boolean = false,
    
    // Vaqt ma'lumotlari
    @SerialName("terms_accepted_at")
    val termsAcceptedAt: String? = null,
    @SerialName("last_login_at")
    val lastLoginAt: String? = null,
    @SerialName("created_at")
    val createdAt: String? = null,
    @SerialName("updated_at")
    val updatedAt: String? = null
)
