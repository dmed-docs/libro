package uz.luka.libro.presantation.components.profile

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.luka.libro.R
import uz.luka.libro.presantation.components.app.ButtonUi
import uz.luka.libro.presantation.components.app.ImageButtonUi
import uz.luka.libro.ui.theme.MainColor

@Composable
fun ProfileButtonsUi(
    onEditProfileClick: () -> Unit = {},
    onShareProfileClick: () -> Unit = {},
    onAddPeopleClick: () -> Unit = {}
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ){
        ButtonUi(
            modifier = Modifier
                .weight(0.3f)
                .height(32.dp),
            isModifier = true,
            text = "Edit Profile" ,
            textSize = 12 ,
            fontFamily = R.font.inter_bold ,
            cornerRadius = 8
        ) {
            onEditProfileClick()
        }
        Spacer(modifier = Modifier.size(4.dp))
        ButtonUi(
            modifier = Modifier
                .weight(0.4f)
                .height(32.dp),
            isModifier = true,
            text = "Share Profile" ,
            textSize = 12 ,
            fontFamily = R.font.inter_bold ,
            cornerRadius = 8
        ) {
            onShareProfileClick()
        }
        Spacer(modifier = Modifier.size(4.dp))

        ImageButtonUi(
            size = 32 ,
            cornerRadius = 8 ,
            color = MainColor ,
            image = R.drawable.ic_add_people
        ) {
            onAddPeopleClick()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileButtonUiPreview() {
    ProfileButtonsUi()
}