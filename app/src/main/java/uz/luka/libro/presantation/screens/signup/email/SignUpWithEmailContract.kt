package uz.luka.libro.presantation.screens.signup.email

import kotlinx.coroutines.flow.StateFlow

interface SignUpWithEmailContract {

    interface ViewModel {
        val uiState: StateFlow<UiState>
        fun onEventDispatcher(intent: Intent)
    }

    data class UiState(
        val email: String = ""
    )

    sealed interface Intent {
        object OnBackClick : Intent
        data class OnEmailChange(val value: String) : Intent
        object OnNextClick : Intent
        object OnSignUpWithPhoneClick : Intent
    }
}
