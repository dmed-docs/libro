package uz.luka.libro.data.repository

import io.github.jan.supabase.storage.Storage
import uz.luka.libro.domain.repository.StorageRepository
import javax.inject.Inject

class StorageRepositoryImpl @Inject constructor(
    private val storage: Storage
) : StorageRepository {
    
    private val bucketName = "avatars"
    
    override suspend fun uploadAvatar(
        userId: String,
        imageBytes: ByteArray,
        fileName: String
    ): Result<String> {
        return try {
            val filePath = "$userId/$fileName"
            
            println("🔵 LIBRO: Avatar yuklanmoqda - path: $filePath, size: ${imageBytes.size} bytes")
            
            // Upload qilish (agar mavjud bo'lsa, yangilash)
            storage.from(bucketName).upload(filePath, imageBytes) {
                upsert = true
            }
            
            // Public URL olish
            val publicUrl = storage.from(bucketName).publicUrl(filePath)
            
            println("✅ LIBRO: Avatar yuklandi - URL: $publicUrl")
            
            Result.success(publicUrl)
        } catch (e: Exception) {
            println("❌ LIBRO: Avatar yuklashda xato: ${e.message}")
            e.printStackTrace()
            Result.failure(e)
        }
    }
    
    override suspend fun deleteAvatar(userId: String): Result<Unit> {
        return try {
            val filePath = "$userId/avatar.jpg"
            
            println("🔵 LIBRO: Avatar o'chirilmoqda - path: $filePath")
            
            storage.from(bucketName).delete(filePath)
            
            println("✅ LIBRO: Avatar o'chirildi")
            
            Result.success(Unit)
        } catch (e: Exception) {
            println("❌ LIBRO: Avatar o'chirishda xato: ${e.message}")
            e.printStackTrace()
            Result.failure(e)
        }
    }
    
    override fun getAvatarUrl(userId: String, fileName: String): String {
        val filePath = "$userId/$fileName"
        return storage.from(bucketName).publicUrl(filePath)
    }
}
