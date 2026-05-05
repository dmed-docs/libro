package uz.luka.libro.utils.navigator

import cafe.adriel.voyager.navigator.Navigator
import kotlinx.coroutines.flow.SharedFlow

typealias navigator = Navigator.() -> Unit

interface NavigationHandler {

    val screenState : SharedFlow<navigator>

}