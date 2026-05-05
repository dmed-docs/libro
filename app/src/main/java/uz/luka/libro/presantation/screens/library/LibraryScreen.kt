package uz.luka.libro.presantation.screens.library

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.MaterialTheme
import cafe.adriel.voyager.core.screen.Screen
import uz.luka.libro.R
import uz.luka.libro.presantation.components.home.SearchFieldUi
import uz.luka.libro.presantation.components.library.LibraryCardUi
import uz.luka.libro.ui.theme.MainColor
import uz.luka.libro.ui.theme.dimens

class LibraryScreen : Screen {
    @Composable
    override fun Content() {
        LibraryScreenContent()
    }
}

@Composable
fun LibraryScreenContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(MaterialTheme.dimens.paddingMedium)
    ) {
        Spacer(modifier = Modifier.size(MaterialTheme.dimens.spacingMedium))
        SearchFieldUi(
            placeholder = "Search for book, e-library or profile" ,
            1
        )
        Spacer(modifier = Modifier.size(MaterialTheme.dimens.spacingSmall))
        Text(
            text = "Category" ,
            style = TextStyle(
                color = MainColor ,
                fontFamily = FontFamily(listOf(Font(R.font.inter_bold))) ,
                fontSize = 14.sp
            )
        )

        Spacer(modifier = Modifier.size(MaterialTheme.dimens.spacingMedium))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2) ,
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimens.spacingMedium) ,
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimens.spacingMedium)
        ) {
            items(count = 16) {
                LibraryCardUi()
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun LibraryScreenPreview() {
    LibraryScreenContent()
}