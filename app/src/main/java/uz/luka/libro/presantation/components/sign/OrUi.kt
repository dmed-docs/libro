package uz.luka.libro.presantation.components.sign

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.luka.libro.R
import uz.luka.libro.ui.theme.DarkGray
import uz.luka.libro.ui.theme.LightGray

@Composable
fun OrUi() {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 25.dp) ,
        verticalAlignment = Alignment.CenterVertically
    ){
        Spacer(
            modifier = Modifier
                .weight(1f)
                .background(LightGray)
                .height(2.dp)
                .clip(RoundedCornerShape(0.5.dp))
        )

        Text(
            modifier = Modifier
                .padding(10.dp),
            text = "or" ,
            style = TextStyle(
                color = DarkGray ,
                fontFamily = FontFamily(listOf(Font(R.font.inter_medium))) ,
                fontSize = 14.sp
            )
        )

        Spacer(
            modifier = Modifier
                .weight(1f)
                .background(LightGray)
                .height(2.dp)
                .clip(RoundedCornerShape(0.5.dp))
        )

    }
}

@Preview(showBackground = true)
@Composable
fun OrUiPreview() {
    OrUi()
}