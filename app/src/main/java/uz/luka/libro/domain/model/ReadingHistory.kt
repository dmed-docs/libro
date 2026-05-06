package uz.luka.libro.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReadingHistory(
    val id: String? = null,
    @SerialName("user_id")
    val userId: String,
    @SerialName("book_id")
    val bookId: String,
    @SerialName("last_page")
    val lastPage: Int = 0,
    @SerialName("progress_percentage")
    val progressPercentage: Int = 0,
    @SerialName("started_at")
    val startedAt: String? = null,
    @SerialName("last_read_at")
    val lastReadAt: String? = null,
    @SerialName("completed_at")
    val completedAt: String? = null
)
