package uz.luka.libro.presantation.screens.signup.terms

import kotlinx.coroutines.flow.StateFlow

interface TermsContract {
    
    interface ViewModel {
        val uiState: StateFlow<UiState>
        fun onEventDispatcher(intent: Intent)
    }
    
    data class UiState(
        val isLoading: Boolean = false
    )
    
    sealed interface Intent {
        object AcceptTerms : Intent
        object OnBackClick : Intent
    }
}
