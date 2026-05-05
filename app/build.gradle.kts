plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.android.hilt)
    kotlin("plugin.serialization") version "2.1.0"
}

android {
    namespace = "uz.luka.libro"
    compileSdk = 36

    defaultConfig {
        applicationId = "uz.luka.libro"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material3.window.size.class1)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)


    //Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    implementation(libs.voyager.hilt)
    //voyager
    implementation(libs.voyager.navigator)
    implementation(libs.voyager.hilt)
    implementation(libs.voyager.transitions)
    implementation(libs.voyager.tabNavigator)
    implementation(libs.voyager.bottomSheetNavigator)

    implementation (libs.riobottomnavigation)

    //systemUiController
    implementation(libs.accompanist.systemuicontroller)

    // splash screen
    implementation(libs.androidx.core.splashscreen)

    // BOM - barcha modul versiyalarini boshqaradi
    implementation(platform("io.github.jan-tennert.supabase:bom:3.2.6"))

    // ----- MAVJUD MODULLAR -----
    implementation("io.github.jan-tennert.supabase:postgrest-kt")   // Ma'lumotlar bazasi (PostgreSQL)
    implementation("io.github.jan-tennert.supabase:auth-kt")        // Autentifikatsiya (login/register)
    implementation("io.github.jan-tennert.supabase:realtime-kt")    // Real vaqt yangilanishlari
    implementation("io.github.jan-tennert.supabase:storage-kt")     // Fayl saqlash
    implementation("io.github.jan-tennert.supabase:functions-kt")   // Edge Functions

    // ----- QO'SHIMCHA PLAGINLAR -----
    implementation("io.github.jan-tennert.supabase:apollo-graphql")  // GraphQL qo'llab-quvvatlashi
    implementation("io.github.jan-tennert.supabase:compose-auth")    // Compose uchun autentifikatsiya UI
    implementation("io.github.jan-tennert.supabase:coil3-integration") // Rasm ko'rsatish (Storage bilan)

    // KTOR HTTP CLIENT - Supabase uchun kerak (YANGILANGAN VERSIYA)
    implementation("io.ktor:ktor-client-core:3.0.3")
    implementation("io.ktor:ktor-client-cio:3.0.3")
    implementation("io.ktor:ktor-client-content-negotiation:3.0.3")
    implementation("io.ktor:ktor-serialization-kotlinx-json:3.0.3")
    implementation("io.ktor:ktor-client-logging:3.0.3")
    implementation("io.ktor:ktor-utils:3.0.3")

}