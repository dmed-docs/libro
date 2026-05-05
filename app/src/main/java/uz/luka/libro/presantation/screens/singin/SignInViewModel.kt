package uz.luka.libro.presantation.screens.singin

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInDirection: SignInDirection
) : SignInContract.ViewModel , ViewModel() {
    override val uiState = MutableStateFlow(SignInContract.UiState())

    private fun reduce(block : (SignInContract.UiState) -> SignInContract.UiState) {
        val oldValue = uiState.value
        uiState.value = block(oldValue)
    }

    override fun onEventDispatcher(intent: SignInContract.Intent) {

        when(intent) {

            SignInContract.Intent.OnClickSignInBtn -> {
                viewModelScope.launch {
                    signInDirection.moveToMainScreen()
                }
            }

            is SignInContract.Intent.OnChangeUserName -> {
                reduce { it.copy(userName = intent.userName) }
            }

            is SignInContract.Intent.OnChangePassword -> {
                reduce { it.copy(password = intent.password) }
            }

            SignInContract.Intent.OnClickPasswordTIc -> {
                reduce { it.copy(passwordIcState = !uiState.value.passwordIcState) }
            }

            SignInContract.Intent.OnClickSignUpText -> {
                viewModelScope.launch {
                    signInDirection.moveToSignUpScreen()
                }
            }

            SignInContract.Intent.OnClickBackBtn -> {
                viewModelScope.launch {
                    signInDirection.back()
                }
            }

        }

    }
}