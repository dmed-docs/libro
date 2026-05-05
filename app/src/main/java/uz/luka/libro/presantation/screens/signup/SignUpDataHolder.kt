package uz.luka.libro.presantation.screens.signup

import javax.inject.Inject
import javax.inject.Singleton

/**
 * SignUp jarayonida ma'lumotlarni saqlash uchun
 */
@Singleton
class SignUpDataHolder @Inject constructor() {
    var email: String = ""
    var phone: String = ""
    var password: String = ""
    var userId: String = ""
    var birthdate: String = ""
    var fullName: String = ""
    
    fun clear() {
        email = ""
        phone = ""
        password = ""
        userId = ""
        birthdate = ""
        fullName = ""
    }
}
