package uz.luka.libro.presantation.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.luka.libro.data.local.UserSession
import uz.luka.libro.domain.model.AuthResult
import uz.luka.libro.domain.repository.UserProfileRepository
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userProfileRepository: UserProfileRepository,
    private val userSession: UserSession
) : ProfileContract.ViewModel, ViewModel() {

    private val _uiState = MutableStateFlow(ProfileContract.UiState())
    override val uiState: StateFlow<ProfileContract.UiState> = _uiState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<ProfileContract.SideEffect>()
    override val sideEffect: SharedFlow<ProfileContract.SideEffect> = _sideEffect.asSharedFlow()

    init {
        loadProfile()
    }

    override fun onEventDispatcher(intent: ProfileContract.Intent) {
        when (intent) {
            ProfileContract.Intent.LoadProfile -> loadProfile()
            ProfileContract.Intent.OnEditProfileClick -> navigateToEditProfile()
            ProfileContract.Intent.OnShareProfileClick -> navigateToShareProfile()
            ProfileContract.Intent.OnSettingsClick -> navigateToSettings()
            ProfileContract.Intent.OnLogoutClick -> logout()
        }
    }

    private fun loadProfile(forceRefresh: Boolean = false) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            val userId = userSession.userId
            if (userId == null) {
                println("❌ LIBRO: User ID topilmadi")
                _uiState.update { 
                    it.copy(
                        isLoading = false, 
                        errorMessage = "User ID topilmadi"
                    ) 
                }
                _sideEffect.emit(ProfileContract.SideEffect.NavigateToLogin)
                return@launch
            }

            println("🔵 LIBRO: Profil yuklanmoqda - userId: $userId, forceRefresh: $forceRefresh")

            when (val result = userProfileRepository.getProfile(userId)) {
                is AuthResult.Success -> {
                    val profile = result.data
                    if (profile != null) {
                        println("✅ LIBRO: Profil yuklandi - ${profile.username}")
                        _uiState.update { 
                            it.copy(
                                isLoading = false, 
                                userProfile = profile
                            ) 
                        }
                    } else {
                        println("❌ LIBRO: Profil topilmadi")
                        _uiState.update { 
                            it.copy(
                                isLoading = false, 
                                errorMessage = "Profil topilmadi"
                            ) 
                        }
                    }
                }
                is AuthResult.Error -> {
                    println("❌ LIBRO: Profil yuklashda xato - ${result.message}")
                    _uiState.update { 
                        it.copy(
                            isLoading = false, 
                            errorMessage = result.message
                        ) 
                    }
                }
                is AuthResult.Loading -> {
                    _uiState.update { it.copy(isLoading = true) }
                }
            }
        }
    }

    private fun navigateToEditProfile() {
        viewModelScope.launch {
            _sideEffect.emit(ProfileContract.SideEffect.NavigateToEditProfile)
        }
    }

    private fun navigateToShareProfile() {
        viewModelScope.launch {
            val profile = _uiState.value.userProfile
            if (profile != null) {
                _sideEffect.emit(
                    ProfileContract.SideEffect.NavigateToShareProfile(
                        username = profile.username,
                        fullName = profile.username
                    )
                )
            }
        }
    }

    private fun navigateToSettings() {
        viewModelScope.launch {
            _sideEffect.emit(ProfileContract.SideEffect.NavigateToSettings)
        }
    }

    private fun logout() {
        viewModelScope.launch {
            userSession.clearSession()
            _sideEffect.emit(ProfileContract.SideEffect.NavigateToLogin)
        }
    }
}
