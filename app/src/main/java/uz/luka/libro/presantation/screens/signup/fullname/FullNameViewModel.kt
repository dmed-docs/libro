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
    private val signUpDirection: SignUpDirection,
    private val signUpDataHolder: uz.luka.libro.presantation.screens.signup.SignUpDataHolder,
    private val userProfileRepository: uz.luka.libro.domain.repository.UserProfileRepository
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
                checkAndProceed()
            }
        }
    }
    
    private fun checkAndProceed() {
        val fullName = _uiState.value.fullName
        
        if (fullName.isEmpty()) {
            return
        }
        
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            
            // Username ni full_name dan yaratish (lowercase va space o'rniga underscore)
            val username = fullName.lowercase().replace(" ", "_")
            
            // Username mavjudligini tekshirish
            when (val result = userProfileRepository.checkUsernameExists(username)) {
                is uz.luka.libro.domain.model.AuthResult.Success -> {
                    if (result.data == true) {
                        // Username allaqachon mavjud
                        _uiState.update { 
                            it.copy(
                                isLoading = false,
                                errorMessage = "Bu username allaqachon band. Boshqa username tanlang."
                            ) 
                        }
                        return@launch
                    }
                    
                    // Username mavjud emas, davom etish mumkin
                    signUpDataHolder.fullName = fullName
                    _uiState.update { it.copy(isLoading = false, errorMessage = null) }
                    signUpDirection.moveToTerms()
                }
                is uz.luka.libro.domain.model.AuthResult.Error -> {
                    _uiState.update { 
                        it.copy(
                            isLoading = false,
                            errorMessage = "Xatolik yuz berdi"
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
