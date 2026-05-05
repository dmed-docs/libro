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
    private val signUpDirection: SignUpDirection
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
                _uiState.update { it.copy(phoneNumber = intent.value) }
            }
            SignUpWithPhoneContract.Intent.OnNextClick -> {
                viewModelScope.launch {
                    signUpDirection.moveToVerificationCode(_uiState.value.phoneNumber)
                }
            }
            SignUpWithPhoneContract.Intent.OnSignUpWithEmailClick -> {
                viewModelScope.launch {
                    signUpDirection.moveToSignUpWithEmail()
                }
            }
        }
    }
}
