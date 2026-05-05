package uz.luka.libro.presantation.screens.login

import kotlinx.coroutines.flow.StateFlow

interface LoginContract {

    interface ViewModel {
        val uiState: StateFlow<UiState>
        fun onEventDispatcher(intent: Intent)
    }

    data class UiState(
        val usernameOrEmail: String = "",
        val password: String = "",
        val passwordVisible: Boolean = false,
        val isLoading: Boolean = false,
        val errorMessage: String? = null
    )

    sealed interface Intent {
        data class OnUsernameOrEmailChange(val value: String) : Intent
        data class OnPasswordChange(val value: String) : Intent
        object OnPasswordVisibilityToggle : Intent
        object OnLoginClick : Intent
        object OnForgotPasswordClick : Intent
        object OnCreateAccountClick : Intent
    }
}
