package uz.luka.libro.domain.repository

interface StorageRepository {
    
    suspend fun uploadAvatar(
        userId: String,
        imageBytes: ByteArray,
        fileName: String = "avatar.jpg"
    ): Result<String>
    
    suspend fun deleteAvatar(userId: String): Result<Unit>
    
    fun getAvatarUrl(userId: String, fileName: String = "avatar.jpg"): String
}
