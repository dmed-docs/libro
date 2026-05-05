package uz.luka.libro.presantation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import uz.luka.libro.R
import uz.luka.libro.presantation.components.home.CategoryUi
import uz.luka.libro.presantation.components.home.ContinueReadingUi
import uz.luka.libro.presantation.components.home.RecomendationUi
import uz.luka.libro.presantation.components.home.SearchFieldUi
import uz.luka.libro.ui.theme.MainColor

class HomeScreen : Screen {
    @Composable
    override fun Content() {
        HomeScreenContent()
    }
}

@Composable
fun HomeScreenContent() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .clip(RoundedCornerShape(22.dp))
                .background(MainColor)
        ) {}

        Column (
            modifier = Modifier
                .padding(16.dp)
        ){
            Text(
                text = "Hi, Luka" ,
                style = TextStyle(
                    color = Color.White ,
                    fontFamily = FontFamily(listOf(Font(R.font.roboto_bold))) ,
                    fontSize = 20.sp
                )
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = "Let's read a book today" ,
                style = TextStyle(
                    color = Color.White ,
                    fontFamily = FontFamily(listOf(Font(R.font.inter_semibold))) ,
                    fontSize = 14.sp
                )
            )
            Spacer(modifier = Modifier.size(35.dp))

            SearchFieldUi(
                placeholder = "Search for book, e-library or profile"
            )

            Spacer(modifier = Modifier.size(16.dp))

            LazyColumn {
                item {
                    ContinueReadingUi()
                    Spacer(modifier = Modifier.size(10.dp))
                }
                item {
                    CategoryUi()
                    Spacer(modifier = Modifier.size(10.dp))
                }
                item {
                    RecomendationUi()
                }
                item {
                    Spacer(modifier = Modifier.size(40.dp))
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreenContent()
}