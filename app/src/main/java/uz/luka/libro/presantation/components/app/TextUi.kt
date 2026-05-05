package uz.luka.libro.presantation.components.app

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import uz.luka.libro.R
import uz.luka.libro.ui.theme.MainColor

@Composable
fun TextUi(
    modifier: Modifier = Modifier,
    text : String,
    color : Color = MainColor,
    fontFamily : Int = R.font.inter_bold,
    fontSize : Int = 14
) {
    Text(
        modifier = modifier,
        text = text ,
        style = TextStyle(
            color = color ,
            fontFamily = FontFamily(listOf(Font(fontFamily))) ,
            fontSize = fontSize.sp
        )
    )
}