package uz.luka.libro.presantation.screens.signup.verification

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
class VerificationCodeViewModel @Inject constructor(
    private val signUpDirection: SignUpDirection
) : VerificationCodeContract.ViewModel, ViewModel() {

    private val _uiState = MutableStateFlow(VerificationCodeContract.UiState())
    override val uiState = _uiState.asStateFlow()

    override fun onEventDispatcher(intent: VerificationCodeContract.Intent) {
        when (intent) {
            VerificationCodeContract.Intent.OnBackClick -> {
                viewModelScope.launch {
                    signUpDirection.back()
                }
            }
            is VerificationCodeContract.Intent.OnCodeChange -> {
                _uiState.update { it.copy(code = intent.value) }
            }
            VerificationCodeContract.Intent.OnNextClick -> {
                // Test: if code is 123456, go to next screen
                if (_uiState.value.code == "123456") {
                    viewModelScope.launch {
                        signUpDirection.moveToBirthdate()
                    }
                }
            }
            VerificationCodeContract.Intent.OnResendCodeClick -> {
                // TODO: Implement resend code logic
            }
        }
    }
}
