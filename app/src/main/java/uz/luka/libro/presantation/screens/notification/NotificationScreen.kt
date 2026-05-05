package uz.luka.libro.presantation.screens.notification

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import uz.luka.libro.R
import uz.luka.libro.presantation.components.app.TextUi
import uz.luka.libro.presantation.components.notification.NotificationMainItem

class NotificationScreen : Screen {
    @Composable
    override fun Content() {
        NotificationScreenContent()
    }
}

@Composable
fun NotificationScreenContent() {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ){
        TextUi(text = "Notification")

        Spacer(modifier = Modifier.size(8.dp))

        LazyColumn {
            item {
                NotificationMainItem(
                    icon = R.drawable.ic_add_people ,
                    texts = listOf(
                        "Activities" ,
                        "See your new followers here."
                    )
                )
                Spacer(modifier = Modifier.size(4.dp))
                NotificationMainItem(
                    icon = R.drawable.ic_time ,
                    texts = listOf(
                        "Activities" ,
                        "FILOSOFI TERAS will EXPIRED TODAY"
                    )
                )
                repeat(12) {
                    NotificationMainItem(
                        icon = 0,
                        texts = listOf(
                            "xojiakbar_2329" ,
                            "1 messages"
                        ) ,
                        image = R.drawable.image ,
                        rightIcon = 2
                    )
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun NotificationScreenPreview() {
    NotificationScreenContent()
}