package uz.luka.libro.presantation.screens.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpMethodViewModel @Inject constructor(
    private val signUpDirection: SignUpDirection
) : SignUpMethodContract.ViewModel, ViewModel() {

    override fun onEventDispatcher(intent: SignUpMethodContract.Intent) {
        when (intent) {
            SignUpMethodContract.Intent.OnBackClick -> {
                viewModelScope.launch {
                    signUpDirection.back()
                }
            }
            SignUpMethodContract.Intent.OnPhoneMethodClick -> {
                viewModelScope.launch {
                    signUpDirection.moveToSignUpWithPhone()
                }
            }
            SignUpMethodContract.Intent.OnEmailMethodClick -> {
                viewModelScope.launch {
                    signUpDirection.moveToSignUpWithEmail()
                }
            }
        }
    }
}
