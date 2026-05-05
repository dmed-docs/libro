package uz.luka.libro.presantation.screens.signup

interface SignUpDirection {
    suspend fun back()
    suspend fun moveToSignUpWithPhone()
    suspend fun moveToSignUpWithEmail()
    suspend fun moveToVerificationCode(phoneOrEmail: String)
    suspend fun moveToBirthdate()
    suspend fun moveToFullName()
    suspend fun moveToTerms()
    suspend fun moveToMain()
}
