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
    private val loginDirection: LoginDirection,
    private val userProfileRepository: uz.luka.libro.domain.repository.UserProfileRepository,
    private val userSession: uz.luka.libro.data.local.UserSession
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
        val email = _uiState.value.usernameOrEmail
        val password = _uiState.value.password
        
        // Validatsiya
        if (email.isEmpty() || password.isEmpty()) {
            _uiState.update { it.copy(errorMessage = "Email va parolni kiriting") }
            return
        }
        
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            
            when (val result = userProfileRepository.login(email, password)) {
                is uz.luka.libro.domain.model.AuthResult.Success -> {
                    val userProfile = result.data
                    if (userProfile != null) {
                        // User session ga saqlash
                        userSession.saveUserSession(
                            userId = userProfile.id ?: "",
                            username = userProfile.username,
                            email = userProfile.email,
                            avatarUrl = userProfile.avatarUrl
                        )
                        
                        println("✅ LIBRO: Login muvaffaqiyatli - User: ${userProfile.username}")
                        _uiState.update { it.copy(isLoading = false) }
                        loginDirection.moveToMain()
                    } else {
                        println("❌ LIBRO: User profil topilmadi")
                        _uiState.update { 
                            it.copy(
                                isLoading = false,
                                errorMessage = "User profil topilmadi"
                            ) 
                        }
                    }
                }
                is uz.luka.libro.domain.model.AuthResult.Error -> {
                    println("❌ LIBRO: Login xatosi: ${result.message}")
                    _uiState.update { 
                        it.copy(
                            isLoading = false,
                            errorMessage = result.message
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
