package uz.luka.libro.presantation.screens.main

import kotlinx.coroutines.flow.StateFlow
import uz.luka.libro.R

interface MainContract {

    interface ViewModel {
        val uiState : StateFlow<UiState>
    }

    data class UiState(
        val listIcons : List<Int> = listOf(
            R.drawable.ic_home ,
            R.drawable.ic_library ,
            R.drawable.ic_notifocation ,
            R.drawable.ic_profile
        ) ,
        val listLabels : List<String> = listOf(
            "Home" ,
            "Library" ,
            "Notification" ,
            "Profile"
        )
    )

    interface Intent {

    }

}