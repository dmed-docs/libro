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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import uz.luka.libro.presantation.components.home.RecomendationCardUi
import uz.luka.libro.presantation.components.profile.ProfileAboutSavedUi
import uz.luka.libro.presantation.components.profile.ProfileMainUi
import uz.luka.libro.presantation.components.profile.ProfileTopContent

class ProfileScreen : Screen {
    @Composable
    override fun Content() {
        ProfileScreenContent()
    }
}

@Composable
fun ProfileScreenContent() {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ){
        ProfileTopContent()

        Spacer(modifier = Modifier.size(10.dp))

        ProfileMainUi()

        Spacer(modifier = Modifier.size(6.dp))

        ProfileAboutSavedUi()

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .shadow(1.dp)
        )

        Spacer(modifier = Modifier.size(10.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(3) ,
            verticalArrangement = Arrangement.spacedBy(16.dp) ,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
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