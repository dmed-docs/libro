package uz.luka.libro.presantation.components.sign

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.luka.libro.R
import uz.luka.libro.ui.theme.MainColor
import uz.luka.libro.ui.theme.MediumGray

@Composable
fun CustomButtonUi() {
    Box (
        modifier = Modifier
            .fillMaxWidth()
            .height(42.dp)
            .border(BorderStroke(2.dp, MainColor), shape = RoundedCornerShape(16.dp)),
    ){
        Image(
            modifier = Modifier
                .padding(8.dp)
                .size(24.dp)
                .align(Alignment.CenterStart),
            painter = painterResource(id = R.drawable.ic_google),
            contentDescription = ""
        )

        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "Continue with Google" ,
            style = TextStyle(
                color = MediumGray ,
                fontFamily = FontFamily(listOf(Font(R.font.inter_semibold))) ,
                fontSize = 14.sp
            )
            )
    }
}

@Preview(showBackground = true)
@Composable
fun CustomButtonUiPreview(){
    CustomButtonUi()
}