package uz.luka.libro.presantation.screens.signup

import kotlinx.coroutines.flow.StateFlow

interface SignUpContract {

    interface ViewModel {

        val uiState : StateFlow<UiState>

        fun onEventDispatcher(intent : Intent)

    }

    data class UiState(
        val userName : String = "",
        val gmail : String = "",
        val password : String = "",
        val rePassword : String = "",
        val passwordIcState : Boolean = false,
        val rePasswordIcState : Boolean = false,
        val userTrailingIconState : Boolean = false,
        val gmailTrailingIconState : Boolean = false ,
        val userNameError : Boolean = false ,
        val userNameMessage : String = "" ,
        val gmailError : Boolean = false,
        val gmailMessage :  String = "" ,
        val passwordError : Boolean = false ,
        val passwordMessage : String = "" ,
        
    )

    interface Intent {

        data class OnChangeUserName(
            val userName: String
        ) : Intent

        data class OnChangeGmail(
            val gmail: String
        ) : Intent

        data class OnChangePassword(
            val password: String
        ) : Intent

        data class OnChangeRePassword(
            val rePassword: String
        ) : Intent

        object OnClickPasswordTIc : Intent

        object OnClickRePasswordTIc : Intent

        object OnClickBackBtn : Intent

        object OnClickSignUpBtn : Intent

        object OnClickUserCrossIc : Intent

        object OnClickGmailCrossIc : Intent

    }

}