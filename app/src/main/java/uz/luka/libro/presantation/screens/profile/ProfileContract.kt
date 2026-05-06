package uz.luka.libro.presantation.screens.profile

import uz.luka.libro.domain.model.UserProfile

interface ProfileContract {
    
    data class UiState(
        val isLoading: Boolean = false,
        val userProfile: UserProfile? = null,
        val errorMessage: String? = null
    )
    
    sealed interface Intent {
        data object LoadProfile : Intent
        data object OnEditProfileClick : Intent
        data object OnShareProfileClick : Intent
        data object OnSettingsClick : Intent
        data object OnLogoutClick : Intent
    }
    
    sealed interface SideEffect {
        data object NavigateToEditProfile : SideEffect
        data class NavigateToShareProfile(val username: String, val fullName: String) : SideEffect
        data object NavigateToSettings : SideEffect
        data object NavigateToLogin : SideEffect
    }
    
    interface ViewModel {
        val uiState: kotlinx.coroutines.flow.StateFlow<UiState>
        val sideEffect: kotlinx.coroutines.flow.SharedFlow<SideEffect>
        fun onEventDispatcher(intent: Intent)
    }
}
