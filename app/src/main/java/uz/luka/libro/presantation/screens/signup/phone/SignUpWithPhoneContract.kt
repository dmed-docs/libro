package uz.luka.libro.presantation.screens.signup.phone

import kotlinx.coroutines.flow.StateFlow

interface SignUpWithPhoneContract {

    interface ViewModel {
        val uiState: StateFlow<UiState>
        fun onEventDispatcher(intent: Intent)
    }

    data class UiState(
        val phoneNumber: String = "+998",
        val errorMessage: String? = null,
        val isLoading: Boolean = false
    )

    sealed interface Intent {
        data object OnBackClick : Intent
        data class OnPhoneNumberChange(val value: String) : Intent
        data object OnNextClick : Intent
        data object OnSignUpWithEmailClick : Intent
    }
}
