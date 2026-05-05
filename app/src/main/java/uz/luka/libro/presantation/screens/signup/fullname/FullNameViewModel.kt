package uz.luka.libro.presantation.screens.signup.fullname

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
class FullNameViewModel @Inject constructor(
    private val signUpDirection: SignUpDirection
) : FullNameContract.ViewModel, ViewModel() {

    private val _uiState = MutableStateFlow(FullNameContract.UiState())
    override val uiState = _uiState.asStateFlow()

    override fun onEventDispatcher(intent: FullNameContract.Intent) {
        when (intent) {
            FullNameContract.Intent.OnBackClick -> {
                viewModelScope.launch {
                    signUpDirection.back()
                }
            }
            is FullNameContract.Intent.OnFullNameChange -> {
                _uiState.update { it.copy(fullName = intent.value) }
            }
            FullNameContract.Intent.OnNextClick -> {
                viewModelScope.launch {
                    signUpDirection.moveToTerms()
                }
            }
        }
    }
}
