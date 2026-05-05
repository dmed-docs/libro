package uz.luka.ailibrary.presantation.components.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.luka.libro.R
import uz.luka.libro.ui.theme.Purple

@Composable
fun CategoryCardUi(
    text : String = "Hello"
) {
    Column (
        modifier = Modifier
            .width(43.dp)
    ){
        Box (
            modifier = Modifier
                .size(43.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Purple)
                .padding(5.dp),
            contentAlignment = Alignment.Center
        ){

        }
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp),
            text = "Action $text" ,
            style = TextStyle(
                color = Color(0xff1C1B1F) ,
                fontFamily = FontFamily(listOf(Font(R.font.inter_medium))) ,
                fontSize = 8.sp ,
                textAlign = TextAlign.Center
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryCardUiPreview() {
    CategoryCardUi()
}