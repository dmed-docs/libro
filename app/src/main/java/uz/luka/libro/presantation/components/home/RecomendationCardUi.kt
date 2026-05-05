package uz.luka.libro.presantation.components.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
import uz.luka.libro.ui.theme.DarkGray
import uz.luka.libro.ui.theme.dimens

@Composable
fun RecomendationCardUi(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(MaterialTheme.dimens.cornerRadiusSmall))
        ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth(),
                painter = painterResource(id = R.drawable.re1),
                contentDescription = "" ,
                contentScale = ContentScale.FillWidth
            )
        }
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .height(MaterialTheme.dimens.recommendationCardTextHeight),
            text = "The Tipping Point" ,
            style = TextStyle(
                color = Color(0xff1C1B1F) ,
                fontFamily = FontFamily(listOf(Font(R.font.inter_bold))) ,
                fontSize = 14.sp ,
                textAlign = TextAlign.Justify
            ),
            overflow = TextOverflow.Ellipsis
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .height(MaterialTheme.dimens.recommendationCardTextHeight),
            text = "Malcolm Gladwell" ,
            style = TextStyle(
                color = DarkGray ,
                fontFamily = FontFamily(listOf(Font(R.font.inter_regular))) ,
                fontSize = 12.sp
            ) ,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RecomendationCardUiPreview() {
    RecomendationCardUi()
}