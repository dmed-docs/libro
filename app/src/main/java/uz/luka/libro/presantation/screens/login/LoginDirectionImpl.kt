package uz.luka.libro.presantation.screens.login

import uz.luka.libro.presantation.screens.main.MainScreen
import uz.luka.libro.presantation.screens.signup.SignUpMethodScreen
import uz.luka.libro.utils.navigator.AppNavigator
import javax.inject.Inject

class LoginDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
) : LoginDirection {

    override suspend fun moveToMain() {
        appNavigator.replaceAll(MainScreen())
    }

    override suspend fun moveToSignUpMethod() {
        appNavigator.push(SignUpMethodScreen())
    }

    override suspend fun moveToForgotPassword() {
        // TODO: Implement forgot password screen
    }
}
