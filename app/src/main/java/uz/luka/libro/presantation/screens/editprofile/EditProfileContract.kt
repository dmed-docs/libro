package uz.luka.libro.presantation.screens.editprofile

import android.net.Uri
import uz.luka.libro.domain.model.UserProfile

interface EditProfileContract {
    
    data class UiState(
        val isLoading: Boolean = false,
        val isSaving: Boolean = false,
        val isUploadingImage: Boolean = false,
        val userProfile: UserProfile? = null,
        val username: String = "",
        val location: String = "",
        val bio: String = "",
        val gender: String = "Prefer not to say",
        val websiteUrl: String = "",
        val profileImageUri: Uri? = null,
        val avatarUrl: String? = null,
        val errorMessage: String? = null,
        val successMessage: String? = null
    )
    
    sealed interface Intent {
        data object LoadProfile : Intent
        data class OnUsernameChange(val value: String) : Intent
        data class OnLocationChange(val value: String) : Intent
        data class OnBioChange(val value: String) : Intent
        data class OnGenderChange(val value: String) : Intent
        data class OnWebsiteUrlChange(val value: String) : Intent
        data class OnProfileImageSelected(val uri: Uri) : Intent
        data object OnSaveClick : Intent
        data object OnBackClick : Intent
    }
    
    sealed interface SideEffect {
        data object NavigateBack : SideEffect
        data class ShowToast(val message: String) : SideEffect
    }
    
    interface ViewModel {
        val uiState: kotlinx.coroutines.flow.StateFlow<UiState>
        val sideEffect: kotlinx.coroutines.flow.SharedFlow<SideEffect>
        fun onEventDispatcher(intent: Intent)
    }
}
