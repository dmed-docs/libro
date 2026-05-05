package uz.luka.libro.presantation.components.profile

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ProfileInfoCardsUi() {
    Row {
        ProfileInfoCardUi(
            number = "21",
            name = "Reads"
        )
        Spacer(modifier = Modifier.size(16.dp))
        ProfileInfoCardUi(
            number = "120",
            name = "Following"
        )
        Spacer(modifier = Modifier.size(16.dp))
        ProfileInfoCardUi(
            number = "300",
            name = "Followers"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileInfoCardsUiPreview() {
    ProfileInfoCardsUi()
}