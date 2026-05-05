package uz.luka.libro.presantation.components.app

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.luka.libro.R
import uz.luka.libro.ui.theme.MainColor

@Composable
fun ImageButtonUi(
    size : Int ,
    cornerRadius : Int ,
    color : Color ,
    image : Int
) {
    Box (
        modifier = Modifier
            .size(size.dp)
            .clip(RoundedCornerShape(cornerRadius.dp))
            .background(color)
            .padding(6.dp)
    ){
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = image) ,
            contentDescription = ""
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ImageButtonUiPreview() {
    ImageButtonUi(
        32 ,
        8 ,
        MainColor ,
        R.drawable.ic_add_people
    )
}