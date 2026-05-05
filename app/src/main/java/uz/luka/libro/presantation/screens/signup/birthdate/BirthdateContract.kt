package uz.luka.libro.presantation.screens.signup.birthdate

import kotlinx.coroutines.flow.StateFlow

interface BirthdateContract {

    interface ViewModel {
        val uiState: StateFlow<UiState>
        fun onEventDispatcher(intent: Intent)
    }

    data class UiState(
        val birthdate: String = ""
    )

    sealed interface Intent {
        object OnBackClick : Intent
        data class OnBirthdateChange(val value: String) : Intent
        object OnNextClick : Intent
    }
}
