package uz.luka.libro.presantation.screens.signup.verification

import kotlinx.coroutines.flow.StateFlow

interface VerificationCodeContract {

    interface ViewModel {
        val uiState: StateFlow<UiState>
        fun onEventDispatcher(intent: Intent)
    }

    data class UiState(
        val code: String = ""
    )

    sealed interface Intent {
        object OnBackClick : Intent
        data class OnCodeChange(val value: String) : Intent
        object OnNextClick : Intent
        object OnResendCodeClick : Intent
    }
}
