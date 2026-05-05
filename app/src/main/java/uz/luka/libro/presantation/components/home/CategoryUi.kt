package uz.luka.libro.presantation.components.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.luka.ailibrary.presantation.components.home.CategoryCardUi
import uz.luka.libro.R
import uz.luka.libro.ui.theme.MainColor

@Composable
fun CategoryUi() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "Category" ,
            style = TextStyle(
                color = MainColor ,
                fontFamily = FontFamily(listOf(Font(R.font.inter_bold))) ,
                fontSize = 14.sp
            )
        )

        Spacer(modifier = Modifier.size(8.dp))

        repeat(2) {
            Row (
                modifier = Modifier
                    .fillMaxWidth() ,
                horizontalArrangement = Arrangement.SpaceAround
            ){
                repeat(7) {
                    CategoryCardUi()
                }
            }
            Spacer(modifier = Modifier.size(2.dp))
        }

    }
}

@Preview(showBackground = true)
@Composable
fun CategoryUiPreview() {
    CategoryUi()
}