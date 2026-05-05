package uz.luka.libro.presantation.screens.intro

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IntroViewModel @Inject constructor(
    private val introDirection: IntroDirection
) : IntroContract.ViewModel , ViewModel() {
    override val uiState = MutableStateFlow(IntroContract.UiState())

    override fun onEventDispatchers(intent: IntroContract.Intent) {
        when(intent) {
            IntroContract.Intent.MoveToSignInScreen -> {
                viewModelScope.launch {
                    introDirection.moveToSignScreen()
                }
            }
        }
    }
}