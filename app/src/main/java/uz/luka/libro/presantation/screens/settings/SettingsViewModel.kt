package uz.luka.libro.presantation.screens.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import uz.luka.libro.data.local.UserSession
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val userSession: UserSession
) : SettingsContract.ViewModel, ViewModel() {

    private val _uiState = MutableStateFlow(SettingsContract.UiState())
    override val uiState: StateFlow<SettingsContract.UiState> = _uiState.asStateFlow()

    private val _sideEffect = MutableSharedFlow<SettingsContract.SideEffect>()
    override val sideEffect: SharedFlow<SettingsContract.SideEffect> = _sideEffect.asSharedFlow()

    override fun onEventDispatcher(intent: SettingsContract.Intent) {
        when (intent) {
            SettingsContract.Intent.OnLogoutClick -> logout()
        }
    }

    private fun logout() {
        viewModelScope.launch {
            // User session ni tozalash
            userSession.clearSession()
            
            // Login screenga o'tish
            _sideEffect.emit(SettingsContract.SideEffect.NavigateToLogin)
        }
    }
}
