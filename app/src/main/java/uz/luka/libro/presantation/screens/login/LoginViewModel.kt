package uz.luka.libro.presantation.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginDirection: LoginDirection
) : LoginContract.ViewModel, ViewModel() {

    private val _uiState = MutableStateFlow(LoginContract.UiState())
    override val uiState = _uiState.asStateFlow()

    override fun onEventDispatcher(intent: LoginContract.Intent) {
        when (intent) {
            is LoginContract.Intent.OnUsernameOrEmailChange -> {
                _uiState.update { it.copy(usernameOrEmail = intent.value) }
            }
            is LoginContract.Intent.OnPasswordChange -> {
                _uiState.update { it.copy(password = intent.value) }
            }
            LoginContract.Intent.OnPasswordVisibilityToggle -> {
                _uiState.update { it.copy(passwordVisible = !it.passwordVisible) }
            }
            LoginContract.Intent.OnLoginClick -> {
                handleLogin()
            }
            LoginContract.Intent.OnForgotPasswordClick -> {
                viewModelScope.launch {
                    loginDirection.moveToForgotPassword()
                }
            }
            LoginContract.Intent.OnCreateAccountClick -> {
                viewModelScope.launch {
                    loginDirection.moveToSignUpMethod()
                }
            }
        }
    }

    private fun handleLogin() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            // TODO: Implement actual login logic
            // For now, just navigate to main screen
            kotlinx.coroutines.delay(1000)
            loginDirection.moveToMain()
            _uiState.update { it.copy(isLoading = false) }
        }
    }
}
