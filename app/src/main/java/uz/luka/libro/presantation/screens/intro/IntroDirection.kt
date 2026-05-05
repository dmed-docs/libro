package uz.luka.libro.presantation.screens.intro

import uz.luka.libro.presantation.screens.singin.SignInScreen
import uz.luka.libro.utils.navigator.AppNavigator
import javax.inject.Inject
import javax.inject.Singleton

interface IntroDirection {
    suspend fun moveToSignScreen()
}

@Singleton
class IntroDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
) : IntroDirection {
    override suspend fun moveToSignScreen() {
        appNavigator.replace(SignInScreen())
    }

}