package uz.luka.libro.presantation.components.sign

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.luka.libro.presantation.components.app.TextUi
import uz.luka.libro.R
import uz.luka.libro.ui.theme.dimens

@Composable
fun RegisterTopUi(
    topText : String = "Sign In",
    onClickBackBtn : () -> Unit = {}
) {
    Row (
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){
        Image(
            modifier = Modifier
                .size(32.dp)
                .clickable {
                    onClickBackBtn()
                },
            painter = painterResource(id = R.drawable.ic_arrow_back),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            text = topText ,
            style = TextStyle(
                fontSize = 16.sp ,
                fontFamily = FontFamily(listOf(Font(R.font.inter_bold))) ,
                color = Color(0xff262626)
            )
        )
    }

    Spacer(modifier = Modifier.size(MaterialTheme.dimens.medium1))

//    Image(
//        modifier = Modifier.height(48.dp),
//        painter = painterResource(id = R.drawable.ic_ijogja1),
//        contentDescription = ""
//    )

    TextUi(
        text = "libro" ,
        fontSize = 50 ,
        fontFamily = R.font.lino_bold
    )

    Spacer(modifier = Modifier.size(MaterialTheme.dimens.medium3))
}

@Preview(showBackground = true)
@Composable
fun SignInTopUiPreview() {
    RegisterTopUi("")
}