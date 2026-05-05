package uz.luka.libro.presantation.screens.singin

import kotlinx.coroutines.flow.StateFlow

interface SignInContract {

    interface ViewModel {

        val uiState : StateFlow<UiState>

        fun onEventDispatcher(intent : Intent)

    }

    data class UiState(
        val userName : String = ""  ,
        val password : String = "" ,
        val passwordIcState : Boolean = false
    )

    interface Intent {

        data class OnChangeUserName(val userName: String) : Intent
        data class OnChangePassword(val password: String) : Intent
        object OnClickSignInBtn : Intent
        object OnClickPasswordTIc : Intent
        object OnClickBackBtn : Intent
        object OnClickSignUpText : Intent

    }

}