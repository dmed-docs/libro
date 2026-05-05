package uz.luka.libro.presantation.components.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.luka.libro.R
import uz.luka.libro.presantation.components.app.TextUi
import uz.luka.libro.ui.theme.DarkGray

@Composable
fun ProfileInfoCardUi(
    number : String  ,
    name : String
) {
    Box (
    ){
        Column (
            modifier = Modifier
                .align(Alignment.Center)
                .padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            TextUi(
                text = number ,
                fontFamily = R.font.inter_semibold ,
                fontSize = 12
            )
            Spacer(modifier = Modifier.size(4.dp))
            TextUi(
                text = name ,
                color = DarkGray ,
                fontSize = 12 ,
                fontFamily = R.font.inter_regular
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileInfoCardUiPreview() {
    ProfileInfoCardUi(
        "21" ,
        "Reads"
    )
}