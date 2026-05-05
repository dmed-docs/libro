package uz.luka.libro.presantation.components.app

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.luka.libro.R
import uz.luka.libro.ui.theme.DarkGray
import uz.luka.libro.ui.theme.LightGray
import uz.luka.libro.ui.theme.dimens

@Composable
fun TextFieldUi(
    modifier: Modifier = Modifier,
    isModifier : Boolean = false ,
    value : String = "",
    onChangeValue : (String) -> Unit = {},
    label : String = "Something",
    trailingIcon : Int = 0,
    leadingIcon : Int = 0 ,
    passwordState : Boolean = true,
    onClickTrailingIc : () -> Unit = {} ,
    borderColor : Color = LightGray ,
    cross : Int = 0 ,
    errorState : Boolean = false ,
    errorMessage : String = "",
    singleLine: Boolean = true,
    maxLines: Int = 1
) {

    OutlinedTextField(
        modifier = if (isModifier) modifier else
            Modifier
                .fillMaxWidth()
                .height(MaterialTheme.dimens.textFieldHeight),
        value = value,
        onValueChange = onChangeValue ,
        placeholder = {
            Text(
                text = label ,
                style = TextStyle(
                    fontFamily = FontFamily(listOf(Font(R.font.publicsans_medium))) ,
                    color = LightGray ,
                    fontSize = 14.sp
                )
            )
        } ,
        textStyle = TextStyle(fontSize = 14.sp),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = borderColor ,
            focusedBorderColor = borderColor ,
            cursorColor = Color.Black ,
            unfocusedTextColor = DarkGray ,
            focusedTextColor = DarkGray ,
            focusedContainerColor = Color.White ,
            unfocusedContainerColor = Color.White
        ) ,
        shape = RoundedCornerShape(MaterialTheme.dimens.cornerRadiusMedium) ,
        isError = errorState,
        supportingText = {
            if (errorState) {
                TextUi(
                    text = errorMessage,
                    color = Color.Red
                )
            }
        },
        leadingIcon = {
            if (leadingIcon != 0) {
                Icon(
                    modifier = Modifier.size(MaterialTheme.dimens.iconSizeMedium),
                    painter = painterResource(id = leadingIcon),
                    contentDescription = "" ,
                    tint = LightGray
                )
            }
        },
        trailingIcon = {
            if (trailingIcon != 0) {
                IconButton(onClick = onClickTrailingIc) {
                    Icon(
                        modifier = Modifier.size(MaterialTheme.dimens.iconSizeMedium),
                        painter = painterResource(id = trailingIcon),
                        contentDescription = "" ,
                        tint = LightGray
                    )
                }
            }
        } ,
        visualTransformation = if (!passwordState && cross != 1) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
        singleLine = singleLine,
        maxLines = maxLines
    )

}

@Preview(showBackground = true)
@Composable
fun TextFieldUiPreview() {
    TextFieldUi()
}