package uz.luka.libro.presantation.screens.settings

import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface SettingsContract {
    
    interface ViewModel {
        val uiState: StateFlow<UiState>
        val sideEffect: SharedFlow<SideEffect>
        fun onEventDispatcher(intent: Intent)
    }
    
    data class UiState(
        val isLoading: Boolean = false
    )
    
    sealed interface Intent {
        data object OnLogoutClick : Intent
    }
    
    sealed interface SideEffect {
        data object NavigateToLogin : SideEffect
    }
}
