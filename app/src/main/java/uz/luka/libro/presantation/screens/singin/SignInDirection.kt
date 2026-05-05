package uz.luka.libro.presantation.screens.singin

import uz.luka.libro.presantation.screens.main.MainScreen
import uz.luka.libro.presantation.screens.signup.SignUpScreen
import uz.luka.libro.utils.navigator.AppNavigator
import javax.inject.Inject
import javax.inject.Singleton

interface SignInDirection {

    suspend fun back()
    suspend fun moveToSignUpScreen()
    suspend fun moveToMainScreen()

}

@Singleton
class SignInDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
) : SignInDirection {

    override suspend fun back() {
        appNavigator.back()
    }

    override suspend fun moveToSignUpScreen() {
        appNavigator.push(SignUpScreen())
    }

    override suspend fun moveToMainScreen() {
        appNavigator.replace(MainScreen())
    }

}