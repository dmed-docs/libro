package uz.luka.libro.presantation.screens.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpDirection: SignUpDirection
) : SignUpContract.ViewModel , ViewModel() {
    override val uiState = MutableStateFlow(SignUpContract.UiState())

    private fun reduce(block : (SignUpContract.UiState) -> SignUpContract.UiState) {
        val oldValue = uiState.value
        uiState.value = block(oldValue)
    }

    override fun onEventDispatcher(intent: SignUpContract.Intent) {

        when(intent) {

            is SignUpContract.Intent.OnChangeUserName -> {
                if (intent.userName.isNotEmpty()) reduce { it.copy(userTrailingIconState = true) }
                else reduce { it.copy(userTrailingIconState = false) }
                reduce { it.copy(userName = intent.userName) }
            }

            is SignUpContract.Intent.OnChangeGmail -> {
                if (intent.gmail.isNotEmpty()) reduce { it.copy(gmailTrailingIconState = true) }
                else reduce { it.copy(gmailTrailingIconState = false) }
                reduce { it.copy(gmail = intent.gmail) }
            }

            is SignUpContract.Intent.OnChangePassword -> {
                reduce { it.copy(password = intent.password) }
            }

            is SignUpContract.Intent.OnChangeRePassword -> {
                reduce { it.copy(rePassword = intent.rePassword) }
            }

            SignUpContract.Intent.OnClickPasswordTIc -> {
                reduce { it.copy(passwordIcState = !uiState.value.passwordIcState) }
            }

            SignUpContract.Intent.OnClickRePasswordTIc -> {
                reduce { it.copy(rePasswordIcState = !uiState.value.rePasswordIcState) }
            }

            SignUpContract.Intent.OnClickBackBtn -> {
                viewModelScope.launch {
                    signUpDirection.back()
                }
            }

            SignUpContract.Intent.OnClickSignUpBtn -> {
                uiState.value.userName.let { userName ->

                }

                uiState.value.gmail.let { gmail ->

                }
            }

            SignUpContract.Intent.OnClickUserCrossIc -> {
                reduce { it.copy(
                    userName = "" ,
                    userTrailingIconState = false
                ) }
            }

            SignUpContract.Intent.OnClickGmailCrossIc -> {
                reduce { it.copy(
                    gmail = "" ,
                    gmailTrailingIconState = false
                ) }
            }

        }

    }
}