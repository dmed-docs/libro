package uz.luka.libro.presantation.components.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.luka.libro.R
import uz.luka.libro.ui.theme.DarkGray
import uz.luka.libro.ui.theme.LightGray
import uz.luka.libro.ui.theme.dimens

@Composable
fun SearchFieldUi(
    placeholder: String ,
    shadow : Int = 10
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(MaterialTheme.dimens.searchBarHeight)
            .shadow(
                elevation = shadow.dp ,
                shape = RoundedCornerShape(MaterialTheme.dimens.cornerRadiusFull)
            )
            .background(Color.White)
            .padding(horizontal = MaterialTheme.dimens.paddingSmall, vertical = MaterialTheme.dimens.paddingTiny)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize() ,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Leading Icon
            Icon(
                modifier = Modifier
                    .size(MaterialTheme.dimens.iconSizeSmall),
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "" ,
                tint = DarkGray
            )

            Spacer(modifier = Modifier.width(MaterialTheme.dimens.spacingSmall))

            // BasicTextField
            BasicTextField(
                value = "",
                onValueChange = {  },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                textStyle = TextStyle(fontSize = 14.sp, color = Color.Black),
                decorationBox = { innerTextField ->
                    Box(
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if ("".isEmpty()) {
                            Text(
                                text = placeholder,
                                color = LightGray,
                                style = TextStyle(fontSize = 14.sp)
                            )
                        }
                        innerTextField()
                    }
                }
            )
        }
    }
}