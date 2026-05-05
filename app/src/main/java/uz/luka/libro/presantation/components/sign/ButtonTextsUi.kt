package uz.luka.libro.presantation.components.sign

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.luka.libro.R
import uz.luka.libro.ui.theme.DarkGray
import uz.luka.libro.ui.theme.MediumGray

@Composable
fun ButtonTextsUi(
    modifier: Modifier ,
    firstText : String  ,
    secondText : String ,
    onClickSecondBtn : () -> Unit
) {
    Row (
        modifier = modifier
    ){
        Text(
            text = firstText ,
            style = TextStyle(
                color = MediumGray ,
                fontFamily = FontFamily(listOf(Font(R.font.inter_medium))) ,
                fontSize = 14.sp
            )
        )
        Spacer(modifier = Modifier.size(5.dp))
        Text(
            modifier = Modifier
                .clickable {
                    onClickSecondBtn()
                } ,
            text = secondText ,
            style = TextStyle(
                color = DarkGray ,
                fontFamily = FontFamily(listOf(Font(R.font.inter_bold))) ,
                fontSize = 14.sp
            )
        )
    }
}
