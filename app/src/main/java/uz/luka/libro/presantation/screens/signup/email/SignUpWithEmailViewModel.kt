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
    private val authRepository: AuthRepository,
    private val signUpDataHolder: uz.luka.libro.presantation.screens.signup.SignUpDataHolder,
    private val userProfileRepository: uz.luka.libro.domain.repository.UserProfileRepository
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
            
            // Email mavjudligini tekshirish
            when (val result = userProfileRepository.checkEmailOrUsernameExists(email)) {
                is uz.luka.libro.domain.model.AuthResult.Success -> {
                    if (result.data == true) {
                        // Email allaqachon mavjud
                        _uiState.update { 
                            it.copy(
                                isLoading = false,
                                errorMessage = "Bu email allaqachon ro'yxatdan o'tgan"
                            ) 
                        }
                        return@launch
                    }
                    
                    // Email mavjud emas, davom etish mumkin
                    signUpDataHolder.email = email
                    println("🔵 LIBRO: Email saqlandi: ${signUpDataHolder.email}")
                    
                    kotlinx.coroutines.delay(500)
                    _uiState.update { it.copy(isLoading = false) }
                    signUpDirection.moveToVerificationCode(email)
                }
                is uz.luka.libro.domain.model.AuthResult.Error -> {
                    _uiState.update { 
                        it.copy(
                            isLoading = false,
                            errorMessage = "Xatolik yuz berdi, qaytadan urinib ko'ring"
                        ) 
                    }
                }
                is uz.luka.libro.domain.model.AuthResult.Loading -> {
                    _uiState.update { it.copy(isLoading = true) }
                }
            }
            
            /* KEYINROQ YOQAMIZ:
            val tempPassword = "TempPass123!"
            
            when (val result = authRepository.signUpWithEmail(email, tempPassword)) {
                is AuthResult.Success -> {
                    _uiState.update { it.copy(isLoading = false) }
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
            */
        }
    }
}
