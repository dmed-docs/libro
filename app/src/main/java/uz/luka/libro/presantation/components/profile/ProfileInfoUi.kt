package uz.luka.libro.presantation.components.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.luka.libro.R
import uz.luka.libro.presantation.components.app.TextUi
import uz.luka.libro.ui.theme.Black
import uz.luka.libro.ui.theme.DarkGray

@Composable
fun ProfileInfoUi(
    fullName: String = "",
    bio: String = ""
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        if (fullName.isNotEmpty()) {
            TextUi(
                text = fullName ,
                color = Black ,
                fontFamily = R.font.inter_bold ,
                fontSize = 12
            )
            Spacer(modifier = Modifier.size(4.dp))
        }
        if (bio.isNotEmpty()) {
            TextUi(
                text = bio ,
                color = DarkGray ,
                fontFamily = R.font.inter_regular ,
                fontSize = 12
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileInfoUiPreview() {
    ProfileInfoUi()
}