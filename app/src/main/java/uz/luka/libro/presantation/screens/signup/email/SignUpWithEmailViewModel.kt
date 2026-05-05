package uz.luka.libro.presantation.screens.signup.email

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
class SignUpWithEmailViewModel @Inject constructor(
    private val signUpDirection: SignUpDirection
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
                _uiState.update { it.copy(email = intent.value) }
            }
            SignUpWithEmailContract.Intent.OnNextClick -> {
                viewModelScope.launch {
                    signUpDirection.moveToVerificationCode(_uiState.value.email)
                }
            }
            SignUpWithEmailContract.Intent.OnSignUpWithPhoneClick -> {
                viewModelScope.launch {
                    signUpDirection.moveToSignUpWithPhone()
                }
            }
        }
    }
}
