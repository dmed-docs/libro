package uz.luka.libro.presantation.components.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.luka.libro.R
import uz.luka.libro.ui.theme.MainColor

@Composable
fun ContinueReadingUi() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        Text(
            text = "Continue Reading" ,
            style = TextStyle(
                color = MainColor ,
                fontFamily = FontFamily(listOf(Font(R.font.inter_bold))) ,
                fontSize = 14.sp
            )
        )

        Spacer(modifier = Modifier.size(8.dp))

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .border(BorderStroke(0.1.dp, Color.LightGray), shape = RoundedCornerShape(8.dp))
                .padding(6.dp)
            ,
            verticalAlignment = Alignment.CenterVertically
        ){
            Column(
                modifier = Modifier
                    .width(68.dp)
                    .height(110.dp)
                    .shadow(
                        elevation = 0.5.dp,
                        shape = RoundedCornerShape(16.dp)
                    ),
            ) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(id = R.drawable.img_continue_reading),
                    contentDescription = ""
                )
            }

            Spacer(modifier = Modifier.size(4.dp))
            Column (
                modifier = Modifier.fillMaxWidth()
            ){
                Text(
                    text = "FILOSOFI TERAS" ,
                    style = TextStyle(
                        color = Color.Black ,
                        fontFamily = FontFamily(listOf(Font(R.font.inter_semibold))) ,
                        fontSize = 14.sp
                    )
                )
                Spacer(modifier = Modifier.size(6.dp))
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    text = "Lebih dari 2.000 tahun lalu, sebuah mazhab filsafat menemukan akar masalah dan juga solusi dari banyak emosi negatif. Stoisisme, atau Filosofi dan juga solusi dari banyak emosi negatif. Stoisisme, atau Filosofi dan juga solusi dari banyak emosi negatif. Stoisisme, atau Filosofi " ,
                    style = TextStyle(
                        color = Color(0xff262626) ,
                        fontFamily = FontFamily(listOf(Font(R.font.inter_regular))) ,
                        fontSize = 12.sp ,
                        textAlign = TextAlign.Justify
                    ) ,
                    overflow = TextOverflow.Ellipsis,
                    softWrap = true
                )

                Spacer(modifier = Modifier.size(2.dp))

                CustomProgressBar(0.2f)
            }
        }

    }
}

@Composable
fun CustomProgressBar(progress: Float) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(5.dp) // Yo'lning balandligi
            .background(Color(0xFFFFCDD2)) // Orqa fon (nofoal yo'l)
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width((progress * 300).dp) // Progressni hisoblash (300 dp misol uchun)
                .background(Color.Red) // Faol rang
        )
    }
}


@Preview(showBackground = true)
@Composable
fun ContinueReadingUiPreview() {
    ContinueReadingUi()
}