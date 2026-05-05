package uz.luka.libro.presantation.components.profile

import androidx.compose.foundation.Image
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.luka.libro.R

@Composable
fun ProfileMainUi() {
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
            ){
                Image(
                    painter = painterResource(id = R.drawable.image) ,
                    contentDescription = "" ,
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            ProfileInfoCardsUi()

        }
        Spacer(modifier = Modifier.size(6.dp))

        ProfileInfoUi()

        Spacer(modifier = Modifier.size(6.dp))

        ProfileButtonsUi()
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileMainUiPreview() {
    ProfileMainUi()
}