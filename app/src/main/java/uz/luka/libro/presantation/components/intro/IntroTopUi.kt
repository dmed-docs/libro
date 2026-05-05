package uz.luka.libro.presantation.components.intro

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.luka.libro.R
import uz.luka.libro.domain.common.IntroCommon
import uz.luka.libro.ui.theme.DarkGray

@Composable
fun IntroTopUi(introCommon : IntroCommon) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White) ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.width(300.dp),
            painter = painterResource(id = introCommon.image),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.size(10.dp))
        Text(
            modifier = Modifier.width(300.dp),
            text = introCommon.title ,
            style = TextStyle(
                color = DarkGray ,
                fontFamily = FontFamily(listOf(Font(R.font.intro_semibold))) ,
                fontSize = 14.sp ,
                textAlign = TextAlign.Center
            )
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            modifier = Modifier.width(300.dp),
            text = introCommon.description ,
            style = TextStyle(
                color = DarkGray ,
                fontFamily = FontFamily(listOf(Font(R.font.inter_regular))) ,
                fontSize = 14.sp ,
                textAlign = TextAlign.Center
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun IntroFirstPreview() {
    IntroTopUi(IntroCommon.FirstPages)
}

@Preview(showBackground = true)
@Composable
fun IntroSecondPreview() {
    IntroTopUi(IntroCommon.SecondPages)
}

@Preview(showBackground = true)
@Composable
fun IntroThirdPreview() {
    IntroTopUi(IntroCommon.ThirdPages)
}