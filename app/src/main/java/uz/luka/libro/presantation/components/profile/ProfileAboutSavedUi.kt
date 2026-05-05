package uz.luka.libro.presantation.components.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.luka.libro.R
import uz.luka.libro.presantation.components.app.TextUi
import uz.luka.libro.ui.theme.DarkGray
import uz.luka.libro.ui.theme.MainColor

@Composable
fun ProfileAboutSavedUi() {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(32.dp)
    ){
        Box (
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.5f)
        ){
            TextUi(
                modifier = Modifier
                    .align(Alignment.Center),
                text = "About" ,
                fontFamily = R.font.inter_medium ,
                fontSize = 12 ,
                color = DarkGray
            )
        }
        Box (
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.5f)
        ){
            TextUi(
                modifier = Modifier
                    .align(Alignment.Center),
                text = "Saved" ,
                fontFamily = R.font.inter_medium ,
                fontSize = 12 ,
                color = MainColor
            )
            Spacer(
                modifier = Modifier
                    .height(2.dp)
                    .width(68.dp)
                    .background(MainColor)
                    .align(Alignment.BottomCenter)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileAboutSavedUiPreview() {
    ProfileAboutSavedUi()
}