package uz.luka.libro.presantation.screens.signup.terms

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.luka.libro.domain.model.AuthResult
import uz.luka.libro.domain.repository.UserProfileRepository
import uz.luka.libro.presantation.screens.signup.SignUpDirection
import javax.inject.Inject

@HiltViewModel
class TermsViewModel @Inject constructor(
    private val signUpDirection: SignUpDirection,
    private val userProfileRepository: UserProfileRepository,
    private val signUpDataHolder: uz.luka.libro.presantation.screens.signup.SignUpDataHolder,
    private val userSession: uz.luka.libro.data.local.UserSession
) : TermsContract.ViewModel, ViewModel() {
    
    private val _uiState = MutableStateFlow(TermsContract.UiState())
    override val uiState = _uiState.asStateFlow()
    
    override fun onEventDispatcher(intent: TermsContract.Intent) {
        when (intent) {
            is TermsContract.Intent.AcceptTerms -> acceptTerms()
            is TermsContract.Intent.OnBackClick -> goBack()
        }
    }
    
    private fun acceptTerms() = viewModelScope.launch {
        _uiState.update { it.copy(isLoading = true) }
        
        // SignUpDataHolder dan ma'lumotlarni olish
        val email = signUpDataHolder.email
        val fullName = signUpDataHolder.fullName
        val birthdate = signUpDataHolder.birthdate
        val password = signUpDataHolder.password
        
        // Username ni full_name dan yaratish (lowercase va space o'rniga underscore)
        val username = fullName.lowercase().replace(" ", "_")
        
        println("🔵 LIBRO: Bazaga saqlash boshlandi...")
        println("🔵 LIBRO: email = $email")
        println("🔵 LIBRO: fullName = $fullName")
        println("🔵 LIBRO: username = $username")
        println("🔵 LIBRO: birthdate = $birthdate")
        println("🔵 LIBRO: password = ${password.take(3)}*** (yashirin)")
        
        // User profilini bazaga saqlash (ID ni database yaratadi)
        when (val result = userProfileRepository.createProfile(
            userId = email, // Email ni userId sifatida yuboramiz
            username = username,
            birthdate = birthdate,
            password = password
        )) {
            is AuthResult.Success -> {
                val userProfile = result.data
                if (userProfile != null) {
                    // User session ga saqlash
                    userSession.saveUserSession(
                        userId = userProfile.id ?: "",
                        username = userProfile.username,
                        email = userProfile.email,
                        avatarUrl = userProfile.avatarUrl
                    )
                    
                    println("✅ LIBRO: Bazaga saqlandi va session yaratildi!")
                    // Ma'lumotlarni tozalash
                    signUpDataHolder.clear()
                    _uiState.update { it.copy(isLoading = false) }
                    // MainScreen ga o'tish
                    signUpDirection.moveToMain()
                } else {
                    println("❌ LIBRO: User profil yaratilmadi")
                    _uiState.update { it.copy(isLoading = false) }
                }
            }
            is AuthResult.Error -> {
                println("❌ LIBRO: Xato: ${result.message}")
                _uiState.update { 
                    it.copy(
                        isLoading = false
                    ) 
                }
            }
            is AuthResult.Loading -> {
                _uiState.update { it.copy(isLoading = true) }
            }
        }
    }
    
    private fun goBack() {
        viewModelScope.launch {
            signUpDirection.back()
        }
    }
}
