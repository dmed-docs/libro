package uz.luka.libro.presantation.components.library

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.luka.libro.R

@Composable
fun LibraryCardUi() {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(Color(0xffF5F5F5))
            .padding(horizontal = 8.dp, vertical = 13.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            modifier = Modifier.width(60.dp),
            text = "Action & Adventure" ,
            style = TextStyle(
                color = Color(0xff262626) ,
                fontFamily = FontFamily(listOf(Font(R.font.inter_medium))) ,
                fontSize = 12.sp
            )
        )
        Spacer(modifier = Modifier.weight(1f))
        Image(
            modifier = Modifier.size(40.dp),
            painter = painterResource(id = R.drawable.world),
            contentDescription = ""
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LibraryCardUiPreview() {
    LibraryCardUi()
}