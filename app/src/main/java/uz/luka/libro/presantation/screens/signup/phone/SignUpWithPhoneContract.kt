package uz.luka.libro.presantation.screens.signup.phone

import kotlinx.coroutines.flow.StateFlow

interface SignUpWithPhoneContract {

    interface ViewModel {
        val uiState: StateFlow<UiState>
        fun onEventDispatcher(intent: Intent)
    }

    data class UiState(
        val phoneNumber: String = ""
    )

    sealed interface Intent {
        object OnBackClick : Intent
        data class OnPhoneNumberChange(val value: String) : Intent
        object OnNextClick : Intent
        object OnSignUpWithEmailClick : Intent
    }
}
