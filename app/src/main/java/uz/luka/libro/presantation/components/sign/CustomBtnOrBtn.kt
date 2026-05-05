package uz.luka.libro.presantation.components.sign

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import uz.luka.libro.presantation.components.app.ButtonUi

@Composable
fun CustomBtnOrBtn(
    firstBtnName : String = "" ,
    clickFirstBtn : () -> Unit = {}
) {
    Spacer(modifier = Modifier.size(14.dp))

    ButtonUi(
        modifier = Modifier
            .fillMaxWidth()
            .height(42.dp) ,
        isModifier = true ,
        text = firstBtnName
    ) {
        clickFirstBtn()
    }

    Spacer(modifier = Modifier.size(16.dp))

    OrUi()

    Spacer(modifier = Modifier.size(16.dp))

    CustomButtonUi()
}