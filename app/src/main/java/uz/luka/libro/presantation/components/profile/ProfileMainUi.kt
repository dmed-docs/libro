package uz.luka.libro.presantation.components.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import uz.luka.libro.R

@Composable
fun ProfileMainUi(
    profileImageRes: Int? = null,
    avatarUrl: String? = null,
    fullName: String = "",
    bio: String = "",
    readsCount: Int = 0,
    followingCount: Int = 0,
    followersCount: Int = 0,
    onEditProfileClick: () -> Unit = {},
    onShareProfileClick: () -> Unit = {}
) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth() ,
            verticalAlignment = Alignment.CenterVertically
        ){
            Box (
                modifier = Modifier
                    .size(72.dp)
                    .clip(RoundedCornerShape(50))
                    .background(Color.LightGray)
            ){
                when {
                    avatarUrl != null -> {
                        AsyncImage(
                            model = avatarUrl,
                            contentDescription = "Profile Avatar",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.size(72.dp)
                        )
                    }
                    profileImageRes != null -> {
                        Image(
                            painter = painterResource(id = profileImageRes),
                            contentDescription = "Profile Image",
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            ProfileInfoCardsUi(
                readsCount = readsCount,
                followingCount = followingCount,
                followersCount = followersCount
            )

        }
        Spacer(modifier = Modifier.size(6.dp))

        ProfileInfoUi(
            fullName = fullName,
            bio = bio
        )

        Spacer(modifier = Modifier.size(6.dp))

        ProfileButtonsUi(
            onEditProfileClick = onEditProfileClick,
            onShareProfileClick = onShareProfileClick
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileMainUiPreview() {
    ProfileMainUi()
}