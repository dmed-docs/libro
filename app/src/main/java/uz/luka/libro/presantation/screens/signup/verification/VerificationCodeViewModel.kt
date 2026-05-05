package uz.luka.libro.presantation.screens.signup.verification

import androidx.lifecycle.SavedStateHandle
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
class VerificationCodeViewModel @Inject constructor(
    private val signUpDirection: SignUpDirection,
    private val authRepository: AuthRepository,
    savedStateHandle: SavedStateHandle
) : VerificationCodeContract.ViewModel, ViewModel() {

    private val _uiState = MutableStateFlow(VerificationCodeContract.UiState())
    override val uiState = _uiState.asStateFlow()
    
    // Email ni screen parametridan olish
    private val email: String = savedStateHandle.get<String>("phoneOrEmail") ?: ""

    override fun onEventDispatcher(intent: VerificationCodeContract.Intent) {
        when (intent) {
            VerificationCodeContract.Intent.OnBackClick -> {
                viewModelScope.launch {
                    signUpDirection.back()
                }
            }
            is VerificationCodeContract.Intent.OnCodeChange -> {
                _uiState.update { it.copy(code = intent.value, errorMessage = null) }
                
                // Agar 6 raqam kiritilsa, avtomatik verify qilish
                if (intent.value.length == 6) {
                    verifyCode(intent.value)
                }
            }
            VerificationCodeContract.Intent.OnNextClick -> {
                verifyCode(_uiState.value.code)
            }
            VerificationCodeContract.Intent.OnResendCodeClick -> {
                resendCode()
            }
        }
    }
    
    private fun verifyCode(code: String) {
        if (code.length != 6) {
            _uiState.update { it.copy(errorMessage = "6 raqamli kod kiriting") }
            return
        }
        
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            
            // HOZIRCHA TEST: Har qanday 6 raqamli kod ishlaydi
            if (code.length == 6) {
                _uiState.update { it.copy(isLoading = false) }
                signUpDirection.moveToPassword() // Password ekraniga o'tish
                return@launch
            }
            
            /* KEYINROQ YOQAMIZ:
            when (val result = authRepository.verifyOtp(email, code)) {
                is AuthResult.Success -> {
                    _uiState.update { it.copy(isLoading = false) }
                    signUpDirection.moveToPassword()
                }
                is AuthResult.Error -> {
                    _uiState.update { 
                        it.copy(
                            isLoading = false, 
                            errorMessage = "Kod noto'g'ri yoki muddati o'tgan"
                        ) 
                    }
                }
                is AuthResult.Loading -> {
                    _uiState.update { it.copy(isLoading = true) }
                }
            }
            */
        }
    }
    
    private fun resendCode() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            
            // TODO: Resend OTP code
            // authRepository.resendOtp(email)
            
            _uiState.update { 
                it.copy(
                    isLoading = false,
                    errorMessage = "Yangi kod yuborildi"
                ) 
            }
        }
    }
}
