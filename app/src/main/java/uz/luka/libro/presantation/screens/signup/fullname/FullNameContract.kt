package uz.luka.libro.presantation.screens.signup.fullname

import kotlinx.coroutines.flow.StateFlow

interface FullNameContract {

    interface ViewModel {
        val uiState: StateFlow<UiState>
        fun onEventDispatcher(intent: Intent)
    }

    data class UiState(
        val fullName: String = ""
    )

    sealed interface Intent {
        object OnBackClick : Intent
        data class OnFullNameChange(val value: String) : Intent
        object OnNextClick : Intent
    }
}
