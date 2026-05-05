package uz.luka.libro.presantation.screens.intro

import kotlinx.coroutines.flow.StateFlow
import uz.luka.libro.domain.common.IntroCommon

interface IntroContract {

    interface ViewModel {

        val uiState : StateFlow<UiState>

        fun onEventDispatchers(intent : Intent)

    }

    data class UiState(
        val pages : List<IntroCommon> = listOf(
            IntroCommon.FirstPages,
            IntroCommon.SecondPages ,
            IntroCommon.ThirdPages
        )
    )

    interface Intent {
        object MoveToSignInScreen : Intent
    }

}