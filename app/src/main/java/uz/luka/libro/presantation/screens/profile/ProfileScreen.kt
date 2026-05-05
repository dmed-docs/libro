package uz.luka.libro.presantation.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import uz.luka.libro.presantation.components.home.RecomendationCardUi
import uz.luka.libro.presantation.components.profile.ProfileAboutSavedUi
import uz.luka.libro.presantation.components.profile.ProfileMainUi
import uz.luka.libro.presantation.components.profile.ProfileTopContent
import uz.luka.libro.presantation.screens.settings.SettingsScreen
import uz.luka.libro.ui.theme.dimens

class ProfileScreen : Screen {
    @Composable
    override fun Content() {
        ProfileScreenContent()
    }
}

@Composable
fun ProfileScreenContent() {
    val navigator = LocalNavigator.currentOrThrow
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(MaterialTheme.dimens.paddingMedium)
    ) {
        ProfileTopContent(
            onMenuClick = {
                navigator.push(SettingsScreen())
            }
        )

        Spacer(modifier = Modifier.size(MaterialTheme.dimens.spacingSmall))

        ProfileMainUi()

        Spacer(modifier = Modifier.size(MaterialTheme.dimens.spacingTiny))

        ProfileAboutSavedUi()

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .shadow(1.dp)
        )

        Spacer(modifier = Modifier.size(MaterialTheme.dimens.spacingSmall))

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimens.spacingMedium),
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimens.spacingMedium)
        ) {
            items(count = 16) {
                RecomendationCardUi()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreenContent()
}