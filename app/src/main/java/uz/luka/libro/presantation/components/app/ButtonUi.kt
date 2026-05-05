package uz.luka.libro.presantation.components.app

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.luka.libro.R
import uz.luka.libro.ui.theme.MainColor

@Composable
fun ButtonUi(
    modifier: Modifier = Modifier,
    isModifier : Boolean = false,
    text : String = "",
    textColor : Color = Color.White,
    textSize : Int = 16,
    fontFamily : Int = R.font.worksans_semibold,
    image : Int = 0,
    backgroundColor : Color = MainColor,
    borderSize : Int = 0,
    borderColor : Color = MainColor,
    cornerRadius : Int = 16,
    onClick : () -> Unit
) {
    Button(
        modifier = if (isModifier) modifier else
            Modifier
                .width(100.dp)
                .height(36.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor
        ),
        border = BorderStroke(borderSize.dp , borderColor),
        shape = RoundedCornerShape(cornerRadius.dp),
        onClick = onClick) {

        if(image != 0) {
            Image(
                modifier = Modifier
                    .padding(6.dp),
                painter = painterResource(id = image) ,
                contentDescription = ""
            )
        }else {
            Text(
                text = text ,
                style = TextStyle(
                    color = textColor ,
                    fontSize = textSize.sp ,
                    fontFamily = FontFamily(listOf(Font(fontFamily)))
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BackButton() {
    ButtonUi(
        text = "Back" ,
        textColor = Color.Black ,
        borderSize = 2 ,
        backgroundColor = Color.White
    ) {

    }
}

@Preview(showBackground = true)
@Composable
fun NextButton() {
    ButtonUi(
        text = "Next"
    ) {

    }
}

@Preview(showBackground = true)
@Composable
fun IntoSignInButton() {
    ButtonUi(
        modifier = Modifier
            .fillMaxWidth()
            .height(36.dp),
        isModifier = true,
        text = "Sign In"
    ) {

    }
}

@Preview(showBackground = true)
@Composable
fun MainButton() {

    ButtonUi(
        modifier = Modifier
            .fillMaxWidth()
            .height(42.dp),
        isModifier = true,
        text = "Sign In"
    ) {

    }

}

@Preview(showBackground = true)
@Composable
fun ProfileButton(){
    ButtonUi(
        modifier = Modifier
            .fillMaxWidth()
            .height(32.dp),
        isModifier = true,
        text = "Edit Profile" ,
        textSize = 12 ,
        fontFamily = R.font.inter_bold ,
        cornerRadius = 8
    ) {

    }
}