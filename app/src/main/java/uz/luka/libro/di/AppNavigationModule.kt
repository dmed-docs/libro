package uz.luka.libro.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.luka.libro.utils.navigator.AppNavigationDispatcher
import uz.luka.libro.utils.navigator.AppNavigator
import uz.luka.libro.utils.navigator.NavigationHandler
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AppNavigationModule {

    @[Binds Singleton]
    fun bindAppNavigator(impl : AppNavigationDispatcher) : AppNavigator

    @[Binds Singleton]
    fun bindNavigationDispatcher(impl : AppNavigationDispatcher) : NavigationHandler

}