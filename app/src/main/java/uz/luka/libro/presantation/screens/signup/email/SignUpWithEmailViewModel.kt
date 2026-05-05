package uz.luka.libro.presantation.screens.signup.email

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.luka.libro.domain.model.AuthResult
import uz.luka.libro.domain.repository.AuthRepository
import uz.luka.libro.presantation.screens.signup.SignUpDirection
import javax.inject.Inject

@HiltViewModel
class SignUpWithEmailViewModel @Inject constructor(
    private val signUpDirection: SignUpDirection,
    private val authRepository: AuthRepository
) : SignUpWithEmailContract.ViewModel, ViewModel() {

    private val _uiState = MutableStateFlow(SignUpWithEmailContract.UiState())
    override val uiState = _uiState.asStateFlow()

    override fun onEventDispatcher(intent: SignUpWithEmailContract.Intent) {
        when (intent) {
            SignUpWithEmailContract.Intent.OnBackClick -> {
                viewModelScope.launch {
                    signUpDirection.back()
                }
            }
            is SignUpWithEmailContract.Intent.OnEmailChange -> {
                _uiState.update { it.copy(email = intent.value, errorMessage = null) }
            }
            SignUpWithEmailContract.Intent.OnNextClick -> {
                signUpWithEmail()
            }
            SignUpWithEmailContract.Intent.OnSignUpWithPhoneClick -> {
                viewModelScope.launch {
                    signUpDirection.moveToSignUpWithPhone()
                }
            }
        }
    }
    
    private fun signUpWithEmail() {
        val email = _uiState.value.email
        
        // Email validatsiya
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _uiState.update { it.copy(errorMessage = "Email noto'g'ri formatda") }
            return
        }
        
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            
            // Temporary password - keyinroq password ekrani qo'shamiz
            val tempPassword = "TempPass123!"
            
            when (val result = authRepository.signUpWithEmail(email, tempPassword)) {
                is AuthResult.Success -> {
                    _uiState.update { it.copy(isLoading = false) }
                    // Verification code ekraniga o'tish
                    signUpDirection.moveToVerificationCode(email)
                }
                is AuthResult.Error -> {
                    _uiState.update { 
                        it.copy(
                            isLoading = false, 
                            errorMessage = result.message
                        ) 
                    }
                }
                is AuthResult.Loading -> {
                    _uiState.update { it.copy(isLoading = true) }
                }
            }
        }
    }
}
