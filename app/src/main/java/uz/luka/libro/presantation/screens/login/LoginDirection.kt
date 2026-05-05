package uz.luka.libro.presantation.screens.login

interface LoginDirection {
    suspend fun moveToMain()
    suspend fun moveToSignUpMethod()
    suspend fun moveToForgotPassword()
}
