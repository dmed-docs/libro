package uz.luka.libro.presantation.screens.signup.password

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.luka.libro.presantation.screens.signup.SignUpDataHolder
import uz.luka.libro.presantation.screens.signup.SignUpDirection
import javax.inject.Inject

@HiltViewModel
class PasswordViewModel @Inject constructor(
    private val signUpDirection: SignUpDirection,
    private val signUpDataHolder: SignUpDataHolder
) : PasswordContract.ViewModel, ViewModel() {

    private val _uiState = MutableStateFlow(PasswordContract.UiState())
    override val uiState = _uiState.asStateFlow()

    override fun onEventDispatcher(intent: PasswordContract.Intent) {
        when (intent) {
            PasswordContract.Intent.OnBackClick -> {
                viewModelScope.launch {
                    signUpDirection.back()
                }
            }
            is PasswordContract.Intent.OnPasswordChange -> {
                _uiState.update { it.copy(password = intent.value, errorMessage = null) }
            }
            is PasswordContract.Intent.OnConfirmPasswordChange -> {
                _uiState.update { it.copy(confirmPassword = intent.value, errorMessage = null) }
            }
            PasswordContract.Intent.TogglePasswordVisibility -> {
                _uiState.update { it.copy(isPasswordVisible = !it.isPasswordVisible) }
            }
            PasswordContract.Intent.ToggleConfirmPasswordVisibility -> {
                _uiState.update { it.copy(isConfirmPasswordVisible = !it.isConfirmPasswordVisible) }
            }
            PasswordContract.Intent.OnNextClick -> {
                validateAndProceed()
            }
        }
    }
    
    private fun validateAndProceed() {
        val password = _uiState.value.password
        val confirmPassword = _uiState.value.confirmPassword
        
        // Validatsiya
        when {
            password.isEmpty() -> {
                _uiState.update { it.copy(errorMessage = "Parol kiriting") }
                return
            }
            password.length < 6 -> {
                _uiState.update { it.copy(errorMessage = "Parol kamida 6 ta belgidan iborat bo'lishi kerak") }
                return
            }
            password != confirmPassword -> {
                _uiState.update { it.copy(errorMessage = "Parollar mos kelmadi") }
                return
            }
        }
        
        // Parolni saqlash
        signUpDataHolder.password = password
        println("🔵 LIBRO: Password saqlandi")
        
        viewModelScope.launch {
            signUpDirection.moveToBirthdate()
        }
    }
}
