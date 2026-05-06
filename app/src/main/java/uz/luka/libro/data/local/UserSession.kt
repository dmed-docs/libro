package uz.luka.libro.data.local

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserSession @Inject constructor(
    @ApplicationContext context: Context
) {
    private val prefs: SharedPreferences = context.getSharedPreferences(
        "libro_user_session",
        Context.MODE_PRIVATE
    )

    companion object {
        private const val KEY_USER_ID = "user_id"
        private const val KEY_USERNAME = "username"
        private const val KEY_FULL_NAME = "full_name"
        private const val KEY_EMAIL = "email"
        private const val KEY_AVATAR_URL = "avatar_url"
        private const val KEY_IS_LOGGED_IN = "is_logged_in"
    }

    // User ID
    var userId: String?
        get() = prefs.getString(KEY_USER_ID, null)
        set(value) = prefs.edit().putString(KEY_USER_ID, value).apply()

    // Username
    var username: String?
        get() = prefs.getString(KEY_USERNAME, null)
        set(value) = prefs.edit().putString(KEY_USERNAME, value).apply()

    // Full Name
    var fullName: String?
        get() = prefs.getString(KEY_FULL_NAME, null)
        set(value) = prefs.edit().putString(KEY_FULL_NAME, value).apply()

    // Email
    var email: String?
        get() = prefs.getString(KEY_EMAIL, null)
        set(value) = prefs.edit().putString(KEY_EMAIL, value).apply()

    // Avatar URL
    var avatarUrl: String?
        get() = prefs.getString(KEY_AVATAR_URL, null)
        set(value) = prefs.edit().putString(KEY_AVATAR_URL, value).apply()

    // Is Logged In
    var isLoggedIn: Boolean
        get() = prefs.getBoolean(KEY_IS_LOGGED_IN, false)
        set(value) = prefs.edit().putBoolean(KEY_IS_LOGGED_IN, value).apply()

    // Save user session
    fun saveUserSession(
        userId: String,
        username: String?,
        email: String?,
        avatarUrl: String?
    ) {
        this.userId = userId
        this.username = username
        this.fullName = username // fullName ham username bilan bir xil
        this.email = email
        this.avatarUrl = avatarUrl
        this.isLoggedIn = true
        
        println("✅ LIBRO: User session saqlandi - userId: $userId, username: $username")
    }

    // Clear user session (logout)
    fun clearSession() {
        prefs.edit().clear().apply()
        println("✅ LIBRO: User session tozalandi")
    }

    // Check if user is logged in
    fun isUserLoggedIn(): Boolean {
        return isLoggedIn && userId != null
    }
}
