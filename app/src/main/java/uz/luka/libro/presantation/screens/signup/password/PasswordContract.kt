package uz.luka.libro.presantation.screens.signup.password

import kotlinx.coroutines.flow.StateFlow

interface PasswordContract {
    interface ViewModel {
        val uiState: StateFlow<UiState>
        fun onEventDispatcher(intent: Intent)
    }
    
    data class UiState(
        val password: String = "",
        val confirmPassword: String = "",
        val isPasswordVisible: Boolean = false,
        val isConfirmPasswordVisible: Boolean = false,
        val errorMessage: String? = null,
        val isLoading: Boolean = false
    )
    
    sealed interface Intent {
        data class OnPasswordChange(val value: String) : Intent
        data class OnConfirmPasswordChange(val value: String) : Intent
        data object TogglePasswordVisibility : Intent
        data object ToggleConfirmPasswordVisibility : Intent
        data object OnNextClick : Intent
        data object OnBackClick : Intent
    }
}
