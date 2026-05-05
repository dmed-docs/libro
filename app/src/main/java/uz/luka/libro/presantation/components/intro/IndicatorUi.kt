package uz.luka.libro.presantation.components.intro


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.luka.libro.ui.theme.MainColor
import uz.luka.libro.ui.theme.SecondaryColor

@Composable
fun IndicatorUi(
    pageSize : Int,
    currentSize : Int,
    selectedColor : Color = MainColor,
    unSelectedColor : Color = SecondaryColor
) {

    Row (
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        repeat(pageSize) {
            Spacer(modifier = Modifier.size(2.5.dp))
            Box(
                modifier = Modifier
                    .height(16.dp)
                    .width(if (it == currentSize) 24.dp else 16.dp)
                    .clip(RoundedCornerShape(50))
                    .background(if (it == currentSize) selectedColor else unSelectedColor),
            ){

            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun IndicatorUiPreview1() {
    IndicatorUi(pageSize = 3, currentSize = 0)
}

@Preview(showBackground = true)
@Composable
fun IndicatorUiPreview2() {
    IndicatorUi(pageSize = 3, currentSize = 1)
}

@Preview(showBackground = true)
@Composable
fun IndicatorUiPreview3() {
    IndicatorUi(pageSize = 3, currentSize = 2)
}