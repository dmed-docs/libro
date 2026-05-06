package uz.luka.libro.presantation.screens.editprofile

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
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
import uz.luka.libro.domain.repository.StorageRepository
import uz.luka.libro.domain.repository.UserProfileRepository
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    private val userProfileRepository: UserProfileRepository,
    private val storageRepository: StorageRepository,
    private val userSession: UserSession,
    @ApplicationContext private val context: Context
) : EditProfileContract.ViewModel, ViewModel() {

    private val _uiState = MutableStateFlow(EditProfileContract.UiState())
    override val uiState: StateFlow<EditProfileContract.UiState> = _uiState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<EditProfileContract.SideEffect>()
    override val sideEffect: SharedFlow<EditProfileContract.SideEffect> = _sideEffect.asSharedFlow()

    init {
        loadProfile()
    }

    override fun onEventDispatcher(intent: EditProfileContract.Intent) {
        when (intent) {
            EditProfileContract.Intent.LoadProfile -> loadProfile()
            is EditProfileContract.Intent.OnUsernameChange -> {
                _uiState.update { it.copy(username = intent.value) }
            }
            is EditProfileContract.Intent.OnLocationChange -> {
                _uiState.update { it.copy(location = intent.value) }
            }
            is EditProfileContract.Intent.OnBioChange -> {
                _uiState.update { it.copy(bio = intent.value) }
            }
            is EditProfileContract.Intent.OnGenderChange -> {
                _uiState.update { it.copy(gender = intent.value) }
            }
            is EditProfileContract.Intent.OnWebsiteUrlChange -> {
                _uiState.update { it.copy(websiteUrl = intent.value) }
            }
            is EditProfileContract.Intent.OnProfileImageSelected -> {
                handleImageSelected(intent.uri)
            }
            EditProfileContract.Intent.OnSaveClick -> saveProfile()
            EditProfileContract.Intent.OnBackClick -> navigateBack()
        }
    }

    private fun loadProfile() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }

            val userId = userSession.userId
            if (userId == null) {
                println("❌ LIBRO: User ID topilmadi")
                _uiState.update { it.copy(isLoading = false) }
                return@launch
            }

            when (val result = userProfileRepository.getProfile(userId)) {
                is AuthResult.Success -> {
                    val profile = result.data
                    if (profile != null) {
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                userProfile = profile,
                                username = profile.username,
                                location = profile.location ?: "",
                                bio = profile.bio ?: "",
                                gender = profile.gender ?: "Prefer not to say",
                                websiteUrl = profile.websiteUrl ?: "",
                                avatarUrl = profile.avatarUrl
                            )
                        }
                    }
                }
                is AuthResult.Error -> {
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

    private fun handleImageSelected(uri: Uri) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    profileImageUri = uri,
                    isUploadingImage = true
                )
            }

            val userId = userSession.userId
            if (userId == null) {
                println("❌ LIBRO: User ID topilmadi")
                _uiState.update {
                    it.copy(
                        isUploadingImage = false,
                        errorMessage = "User ID topilmadi"
                    )
                }
                return@launch
            }

            try {
                // Uri dan byte array olish
                val inputStream = context.contentResolver.openInputStream(uri)
                val imageBytes = inputStream?.readBytes()
                inputStream?.close()

                if (imageBytes == null) {
                    println("❌ LIBRO: Rasmni o'qib bo'lmadi")
                    _uiState.update {
                        it.copy(
                            isUploadingImage = false,
                            errorMessage = "Rasmni o'qib bo'lmadi"
                        )
                    }
                    return@launch
                }

                // Supabase ga yuklash
                val result = storageRepository.uploadAvatar(userId, imageBytes)

                result.onSuccess { avatarUrl ->
                    _uiState.update {
                        it.copy(
                            isUploadingImage = false,
                            avatarUrl = avatarUrl
                        )
                    }
                    _sideEffect.emit(EditProfileContract.SideEffect.ShowToast("Rasm yuklandi"))
                }.onFailure { error ->
                    println("❌ LIBRO: Rasm yuklashda xato: ${error.message}")
                    error.printStackTrace()
                    _uiState.update {
                        it.copy(
                            isUploadingImage = false,
                            errorMessage = "Rasm yuklashda xato: ${error.message}"
                        )
                    }
                }
            } catch (e: Exception) {
                println("❌ LIBRO: Exception: ${e.message}")
                e.printStackTrace()
                _uiState.update {
                    it.copy(
                        isUploadingImage = false,
                        errorMessage = e.message
                    )
                }
            }
        }
    }

    private fun saveProfile() {
        viewModelScope.launch {
            _uiState.update { it.copy(isSaving = true) }

            val userId = userSession.userId ?: return@launch
            val state = _uiState.value

            // Username o'zgargan bo'lsa, tekshirish
            val currentUsername = userSession.username
            val newUsername = state.username.trim()
            
            if (newUsername.isEmpty()) {
                _uiState.update {
                    it.copy(
                        isSaving = false,
                        errorMessage = "Username bo'sh bo'lishi mumkin emas"
                    )
                }
                _sideEffect.emit(EditProfileContract.SideEffect.ShowToast("Username bo'sh bo'lishi mumkin emas"))
                return@launch
            }
            
            if (currentUsername != newUsername) {
                // Username mavjudligini tekshirish
                when (val checkResult = userProfileRepository.checkUsernameExists(newUsername)) {
                    is AuthResult.Success -> {
                        if (checkResult.data == true) {
                            _uiState.update {
                                it.copy(
                                    isSaving = false,
                                    errorMessage = "Bu username allaqachon band"
                                )
                            }
                            _sideEffect.emit(EditProfileContract.SideEffect.ShowToast("Bu username allaqachon band"))
                            return@launch
                        }
                    }
                    is AuthResult.Error -> {
                        _uiState.update {
                            it.copy(
                                isSaving = false,
                                errorMessage = checkResult.message
                            )
                        }
                        return@launch
                    }
                    is AuthResult.Loading -> {}
                }
            }

            // Profil yangilash
            when (val result = userProfileRepository.updateProfile(
                userId = userId,
                username = newUsername,
                birthdate = null,
                avatarUrl = state.avatarUrl,
                bio = state.bio.ifEmpty { null },
                location = state.location.ifEmpty { null },
                gender = if (state.gender != "Prefer not to say") state.gender else null,
                websiteUrl = state.websiteUrl.ifEmpty { null }
            )) {
                is AuthResult.Success -> {
                    // UserSession ni yangilash
                    userSession.saveUserSession(
                        userId = result.data.id ?: userId,
                        username = result.data.username,
                        email = result.data.email,
                        avatarUrl = result.data.avatarUrl
                    )
                    
                    _uiState.update {
                        it.copy(
                            isSaving = false,
                            successMessage = "Profil yangilandi"
                        )
                    }
                    _sideEffect.emit(EditProfileContract.SideEffect.ShowToast("Profil yangilandi"))
                    _sideEffect.emit(EditProfileContract.SideEffect.NavigateBack)
                }
                is AuthResult.Error -> {
                    _uiState.update {
                        it.copy(
                            isSaving = false,
                            errorMessage = result.message
                        )
                    }
                    _sideEffect.emit(EditProfileContract.SideEffect.ShowToast(result.message ?: "Xatolik"))
                }
                is AuthResult.Loading -> {
                    _uiState.update { it.copy(isSaving = true) }
                }
            }
        }
    }

    private fun navigateBack() {
        viewModelScope.launch {
            _sideEffect.emit(EditProfileContract.SideEffect.NavigateBack)
        }
    }
}
