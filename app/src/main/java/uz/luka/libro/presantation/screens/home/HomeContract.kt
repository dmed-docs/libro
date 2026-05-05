package uz.luka.libro.presantation.screens.home

interface HomeContract {

    interface ViewModel {

    }

    data class UiState(
        val searchValue : String
    )

    interface Intent {

        data class OnChangeSearchValue(
            val searchValue: String
        ) : Intent

    }

}