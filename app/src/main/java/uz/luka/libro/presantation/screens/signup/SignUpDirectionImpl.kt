package uz.luka.libro.presantation.screens.signup

import uz.luka.libro.presantation.screens.main.MainScreen
import uz.luka.libro.presantation.screens.signup.birthdate.BirthdateScreen
import uz.luka.libro.presantation.screens.signup.email.SignUpWithEmailScreen
import uz.luka.libro.presantation.screens.signup.fullname.FullNameScreen
import uz.luka.libro.presantation.screens.signup.password.PasswordScreen
import uz.luka.libro.presantation.screens.signup.phone.SignUpWithPhoneScreen
import uz.luka.libro.presantation.screens.signup.terms.TermsScreen
import uz.luka.libro.presantation.screens.signup.verification.VerificationCodeScreen
import uz.luka.libro.utils.navigator.AppNavigator
import javax.inject.Inject

class SignUpDirectionImpl @Inject constructor(
    private val appNavigator: AppNavigator
) : SignUpDirection {

    override suspend fun back() {
        appNavigator.back()
    }

    override suspend fun moveToSignUpWithPhone() {
        appNavigator.push(SignUpWithPhoneScreen())
    }

    override suspend fun moveToSignUpWithEmail() {
        appNavigator.push(SignUpWithEmailScreen())
    }

    override suspend fun moveToVerificationCode(phoneOrEmail: String) {
        appNavigator.push(VerificationCodeScreen(phoneOrEmail))
    }

    override suspend fun moveToPassword() {
        appNavigator.push(PasswordScreen())
    }

    override suspend fun moveToBirthdate() {
        appNavigator.push(BirthdateScreen())
    }

    override suspend fun moveToFullName() {
        appNavigator.push(FullNameScreen())
    }

    override suspend fun moveToTerms() {
        appNavigator.push(TermsScreen())
    }

    override suspend fun moveToMain() {
        appNavigator.replaceAll(MainScreen())
    }
}
