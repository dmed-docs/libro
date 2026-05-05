package uz.luka.libro.presantation.screens.signup.birthdate

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
class BirthdateViewModel @Inject constructor(
    private val signUpDirection: SignUpDirection
) : BirthdateContract.ViewModel, ViewModel() {

    private val _uiState = MutableStateFlow(BirthdateContract.UiState())
    override val uiState = _uiState.asStateFlow()

    override fun onEventDispatcher(intent: BirthdateContract.Intent) {
        when (intent) {
            BirthdateContract.Intent.OnBackClick -> {
                viewModelScope.launch {
                    signUpDirection.back()
                }
            }
            is BirthdateContract.Intent.OnBirthdateChange -> {
                _uiState.update { it.copy(birthdate = intent.value) }
            }
            BirthdateContract.Intent.OnNextClick -> {
                viewModelScope.launch {
                    signUpDirection.moveToFullName()
                }
            }
        }
    }
}
