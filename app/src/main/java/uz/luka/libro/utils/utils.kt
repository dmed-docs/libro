package uz.luka.libro.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import uz.luka.libro.ui.theme.CompactDimens
import uz.luka.libro.ui.theme.Dimens

@Composable
fun Utils(
    appDimens: Dimens,
    content : @Composable () -> Unit
) {

    val appDimen = remember {
        appDimens
    }

    CompositionLocalProvider(LocalAppDimens provides appDimen) {
        content()
    }

}


val LocalAppDimens = compositionLocalOf {
    CompactDimens
}
