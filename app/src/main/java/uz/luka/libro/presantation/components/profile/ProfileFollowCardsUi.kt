package uz.luka.libro.presantation.components.profile

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ProfileInfoCardsUi(
    readsCount: Int = 0,
    followingCount: Int = 0,
    followersCount: Int = 0
) {
    Row {
        ProfileInfoCardUi(
            number = readsCount.toString(),
            name = "Reads"
        )
        Spacer(modifier = Modifier.size(16.dp))
        ProfileInfoCardUi(
            number = followingCount.toString(),
            name = "Following"
        )
        Spacer(modifier = Modifier.size(16.dp))
        ProfileInfoCardUi(
            number = followersCount.toString(),
            name = "Followers"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileInfoCardsUiPreview() {
    ProfileInfoCardsUi()
}