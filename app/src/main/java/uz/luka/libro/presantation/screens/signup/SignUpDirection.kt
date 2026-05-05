package uz.luka.libro.presantation.screens.signup

import uz.luka.libro.presantation.screens.main.MainScreen
import uz.luka.libro.utils.navigator.AppNavigator
import javax.inject.Inject
import javax.inject.Singleton

interface SignUpDirection {

    suspend fun back()
    suspend fun moveToMainScreen()

}

@Singleton
class SignUpDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
) : SignUpDirection {

    override suspend fun back() {
        appNavigator.back()
    }

    override suspend fun moveToMainScreen() {
        appNavigator.replace(MainScreen())
    }

}