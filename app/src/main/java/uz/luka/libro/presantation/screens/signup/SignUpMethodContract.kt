package uz.luka.libro.presantation.screens.signup

interface SignUpMethodContract {

    interface ViewModel {
        fun onEventDispatcher(intent: Intent)
    }

    sealed interface Intent {
        object OnBackClick : Intent
        object OnPhoneMethodClick : Intent
        object OnEmailMethodClick : Intent
    }
}
