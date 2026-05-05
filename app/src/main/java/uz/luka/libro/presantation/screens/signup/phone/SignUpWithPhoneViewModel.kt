package uz.luka.libro.presantation.screens.signup.phone

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
class SignUpWithPhoneViewModel @Inject constructor(
    private val signUpDirection: SignUpDirection,
    private val signUpDataHolder: uz.luka.libro.presantation.screens.signup.SignUpDataHolder,
    private val userProfileRepository: uz.luka.libro.domain.repository.UserProfileRepository
) : SignUpWithPhoneContract.ViewModel, ViewModel() {

    private val _uiState = MutableStateFlow(SignUpWithPhoneContract.UiState())
    override val uiState = _uiState.asStateFlow()

    override fun onEventDispatcher(intent: SignUpWithPhoneContract.Intent) {
        when (intent) {
            SignUpWithPhoneContract.Intent.OnBackClick -> {
                viewModelScope.launch {
                    signUpDirection.back()
                }
            }
            is SignUpWithPhoneContract.Intent.OnPhoneNumberChange -> {
                _uiState.update { it.copy(phoneNumber = intent.value, errorMessage = null) }
            }
            SignUpWithPhoneContract.Intent.OnNextClick -> {
                checkAndProceed()
            }
            SignUpWithPhoneContract.Intent.OnSignUpWithEmailClick -> {
                viewModelScope.launch {
                    signUpDirection.moveToSignUpWithEmail()
                }
            }
        }
    }
    
    private fun checkAndProceed() {
        val phone = _uiState.value.phoneNumber
        
        if (phone.isEmpty()) {
            _uiState.update { it.copy(errorMessage = "Telefon raqamini kiriting") }
            return
        }
        
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            
            when (val result = userProfileRepository.checkPhoneExists(phone)) {
                is uz.luka.libro.domain.model.AuthResult.Success -> {
                    if (result.data == true) {
                        _uiState.update { 
                            it.copy(
                                isLoading = false,
                                errorMessage = "Bu telefon raqam allaqachon ro'yxatdan o'tgan"
                            ) 
                        }
                        return@launch
                    }
                    
                    signUpDataHolder.phone = phone
                    _uiState.update { it.copy(isLoading = false) }
                    signUpDirection.moveToVerificationCode(phone)
                }
                is uz.luka.libro.domain.model.AuthResult.Error -> {
                    _uiState.update { 
                        it.copy(
                            isLoading = false,
                            errorMessage = "Xatolik yuz berdi"
                        ) 
                    }
                }
                is uz.luka.libro.domain.model.AuthResult.Loading -> {
                    _uiState.update { it.copy(isLoading = true) }
                }
            }
        }
    }
}
