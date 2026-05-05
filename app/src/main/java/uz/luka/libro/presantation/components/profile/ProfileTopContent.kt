package uz.luka.libro.presantation.components.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.luka.libro.R
import uz.luka.libro.presantation.components.app.TextUi
import uz.luka.libro.ui.theme.Black

@Composable
fun ProfileTopContent(
    onMenuClick: () -> Unit = {}
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp) ,
        verticalAlignment = Alignment.CenterVertically
    ){
        TextUi(
            text = "xojiakbar_2329" ,
            color = Black ,
            fontSize = 14 ,
            fontFamily = R.font.inter_bold
        )
        Spacer(modifier = Modifier.weight(1f))

        Image(
            modifier = Modifier
                .width(18.dp)
                .height(12.dp)
                .clickable { onMenuClick() },
            painter = painterResource(id = R.drawable.ic_menu),
            contentDescription = "Menu"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileTopPreview() {
    ProfileTopContent()
}