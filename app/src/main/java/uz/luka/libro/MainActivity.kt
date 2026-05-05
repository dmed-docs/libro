package uz.luka.libro

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.luka.libro.presantation.screens.intro.IntroScreen
import uz.luka.libro.presantation.screens.splash.SplashViewModel
import uz.luka.libro.ui.theme.LibroTheme
import uz.luka.libro.utils.navigator.NavigationHandler
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val splashViewModel by viewModels<SplashViewModel>()
    @Inject
    lateinit var navigationHandler: NavigationHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplash()
        setContent {
            LibroTheme {

                Navigator(screen = IntroScreen()) { navigator ->
                    LaunchedEffect(key1 = navigator) {
                        navigationHandler.screenState.onEach { it.invoke(navigator) }
                            .launchIn(lifecycleScope)
                    }
                    SlideTransition(navigator = navigator)
                }
            }
        }
    }

    private fun installSplash() {
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                !splashViewModel.isReady.value
            }
            setOnExitAnimationListener {screen ->
                val zoomX = ObjectAnimator.ofFloat(
                    screen.iconView ,
                    View.SCALE_X ,
                    0.5f ,
                    0.0f
                )
                zoomX.interpolator = OvershootInterpolator()
                zoomX.duration = 500L
                zoomX.doOnEnd { screen.remove() }

                val zoomY = ObjectAnimator.ofFloat(
                    screen.iconView ,
                    View.SCALE_Y ,
                    0.5f ,
                    0.0f
                )
                zoomY.interpolator = OvershootInterpolator()
                zoomY.duration = 500L
                zoomY.doOnEnd { screen.remove() }

                zoomX.start()
                zoomY.start()
            }
        }
    }
}

