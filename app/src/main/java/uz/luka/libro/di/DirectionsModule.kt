package uz.luka.libro.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.luka.libro.presantation.screens.intro.IntroDirection
import uz.luka.libro.presantation.screens.intro.IntroDirectionImpl
import uz.luka.libro.presantation.screens.signup.SignUpDirection
import uz.luka.libro.presantation.screens.signup.SignUpDirectionImpl
import uz.luka.libro.presantation.screens.singin.SignInDirection
import uz.luka.libro.presantation.screens.singin.SignInDirectionImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DirectionsModule {

    @[Binds Singleton]
    fun bindIntroDirection(impl : IntroDirectionImpl) : IntroDirection

    @[Binds Singleton]
    fun bindSignUpDirection(impl : SignUpDirectionImpl) : SignUpDirection

    @[Binds Singleton]
    fun bindSignInDirection(impl : SignInDirectionImpl) : SignInDirection

}