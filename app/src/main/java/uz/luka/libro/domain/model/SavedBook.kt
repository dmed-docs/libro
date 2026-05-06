package uz.luka.libro.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SavedBook(
    val id: String? = null,
    @SerialName("user_id")
    val userId: String,
    @SerialName("book_id")
    val bookId: String,
    @SerialName("created_at")
    val createdAt: String? = null
)
