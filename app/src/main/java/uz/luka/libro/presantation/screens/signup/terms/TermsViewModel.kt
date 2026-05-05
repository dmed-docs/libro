package uz.luka.libro.presantation.screens.signup.terms

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.luka.libro.presantation.screens.signup.SignUpDirection
import javax.inject.Inject

@HiltViewModel
class TermsViewModel @Inject constructor(
    private val signUpDirection: SignUpDirection
) : TermsContract.ViewModel, ViewModel() {
    
    private val _uiState = MutableStateFlow(TermsContract.UiState())
    override val uiState = _uiState.asStateFlow()
    
    override fun onEventDispatcher(intent: TermsContract.Intent) {
        when (intent) {
            is TermsContract.Intent.AcceptTerms -> acceptTerms()
            is TermsContract.Intent.OnBackClick -> goBack()
        }
    }
    
    private fun acceptTerms() {
        _uiState.update { it.copy(isLoading = true) }
        
        viewModelScope.launch {
            // Test: Shartlarni qabul qilish - MainScreen ga o'tish
            signUpDirection.moveToMain()
        }
    }
    
    private fun goBack() {
        viewModelScope.launch {
            signUpDirection.back()
        }
    }
}
