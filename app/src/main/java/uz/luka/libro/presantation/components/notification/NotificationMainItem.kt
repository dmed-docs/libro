package uz.luka.libro.presantation.components.notification

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.luka.libro.R
import uz.luka.libro.presantation.components.app.TextUi
import uz.luka.libro.ui.theme.MainColor

@Composable
fun NotificationMainItem(
    icon : Int ,
    texts : List<String> ,
    image : Int = 0 ,
    rightIcon : Int = 0
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(Color.White)
            .padding(vertical = 4.dp) ,
        verticalAlignment = Alignment.CenterVertically
    ){
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(50))
                .background(MainColor)
                .padding(if (image == 0) 8.dp else 0.dp)
        ){
            Image(
                modifier = Modifier.align(Alignment.Center),
                painter = painterResource(id = if (image == 0) icon else image) ,
                contentDescription = "" ,
                contentScale = if (image == 0) ContentScale.Inside else ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.size(12.dp))

        Column {
            TextUi(
                text = texts[0] ,
                color = Color.Black ,
                fontSize = 14 ,
                fontFamily = R.font.inter_semibold
            )
            Spacer(modifier = Modifier.size(2.dp))
            TextUi(
                text = texts[1] ,
                color = Color.Black ,
                fontSize = 12 ,
                fontFamily = R.font.inter_semibold
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        if (rightIcon != 0) {
            if (rightIcon == 1) {
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .clip(RoundedCornerShape(50))
                        .background(MainColor)
                )
            }
        }else {
            Image(
                modifier = Modifier.width(5.dp),
                painter = painterResource(id = R.drawable.ic_arrow_right) ,
                contentDescription = ""
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun NotificationMainItemPreview() {
    NotificationMainItem(
        R.drawable.ic_add_people ,
        listOf("xojiakbar_2329" , "1 messages") ,
        image = R.drawable.image ,
        rightIcon = 1
    )
}