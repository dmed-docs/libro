package uz.luka.libro.presantation.screens.chat

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil3.compose.AsyncImage
import uz.luka.libro.R
import uz.luka.libro.ui.theme.MainColor

data class ChatScreen(
    val userId: String,
    val username: String,
    val avatarUrl: String?
) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        
        ChatScreenContent(
            username = username,
            userInfo = "Bookworm • 67 books read",
            avatarUrl = avatarUrl,
            onBackClick = { navigator.pop() },
            onMenuClick = { /* TODO */ }
        )
    }
}

@Composable
fun ChatScreenContent(
    username: String = "Angelica Rain",
    userInfo: String = "Bookworm • 67 books read",
    avatarUrl: String? = null,
    messages: List<ChatMessage> = sampleMessages,
    onBackClick: () -> Unit = {},
    onMenuClick: () -> Unit = {},
    onSendMessage: (String) -> Unit = {}
) {
    var messageText by remember { mutableStateOf("") }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Top Bar
        ChatTopBar(
            username = username,
            userInfo = userInfo,
            avatarUrl = avatarUrl,
            onBackClick = onBackClick,
            onMenuClick = onMenuClick
        )
        
        // Messages List
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(messages) { message ->
                when (message) {
                    is ChatMessage.DateSeparator -> {
                        DateSeparatorItem(date = message.date)
                    }
                    is ChatMessage.Text -> {
                        MessageItem(
                            message = message.text,
                            isFromMe = message.isFromMe,
                            avatarUrl = if (!message.isFromMe) avatarUrl else null,
                            time = message.time
                        )
                    }
                    is ChatMessage.Book -> {
                        BookMessageItem(
                            bookTitle = message.bookTitle,
                            bookAuthor = message.bookAuthor,
                            bookCoverUrl = message.bookCoverUrl,
                            isFromMe = message.isFromMe,
                            avatarUrl = if (!message.isFromMe) avatarUrl else null
                        )
                    }
                }
            }
        }
        
        // Message Input
        ChatInputBar(
            messageText = messageText,
            onMessageChange = { messageText = it },
            onSendClick = {
                if (messageText.isNotBlank()) {
                    onSendMessage(messageText)
                    messageText = ""
                }
            },
            onVoiceClick = { /* TODO */ },
            onAttachClick = { /* TODO */ }
        )
    }
}

@Composable
fun ChatTopBar(
    username: String,
    userInfo: String,
    avatarUrl: String?,
    onBackClick: () -> Unit,
    onMenuClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .background(Color.White)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Back Button
        Icon(
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = "Back",
            tint = Color.Black,
            modifier = Modifier
                .size(24.dp)
                .clickable { onBackClick() }
        )
        
        Spacer(modifier = Modifier.width(12.dp))
        
        // Avatar
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color.LightGray)
        ) {
            if (avatarUrl != null) {
                AsyncImage(
                    model = avatarUrl,
                    contentDescription = "Avatar",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
        
        Spacer(modifier = Modifier.width(12.dp))
        
        // User Info
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = username,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = userInfo,
                fontSize = 12.sp,
                color = Color.Gray
            )
        }
        
        // Menu Button (3 dots)
        Icon(
            painter = painterResource(id = R.drawable.ic_menu),
            contentDescription = "Menu",
            tint = Color.Black,
            modifier = Modifier
                .size(24.dp)
                .clickable { onMenuClick() }
        )
    }
}

@Composable
fun DateSeparatorItem(date: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = date,
            fontSize = 12.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun MessageItem(
    message: String,
    isFromMe: Boolean,
    avatarUrl: String?,
    time: String? = null
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (isFromMe) Arrangement.End else Arrangement.Start
    ) {
        if (!isFromMe && avatarUrl != null) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray)
            ) {
                AsyncImage(
                    model = avatarUrl,
                    contentDescription = "Avatar",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
        }
        
        Box(
            modifier = Modifier
                .widthIn(max = 280.dp)
                .clip(
                    RoundedCornerShape(
                        topStart = if (isFromMe) 16.dp else 4.dp,
                        topEnd = if (isFromMe) 4.dp else 16.dp,
                        bottomStart = 16.dp,
                        bottomEnd = 16.dp
                    )
                )
                .background(
                    if (isFromMe) MainColor else Color(0xFFF0F0F0)
                )
                .padding(12.dp)
        ) {
            Text(
                text = message,
                fontSize = 14.sp,
                color = if (isFromMe) Color.White else Color.Black,
                lineHeight = 20.sp
            )
        }
        
        if (isFromMe && avatarUrl != null) {
            Spacer(modifier = Modifier.width(8.dp))
        }
    }
}

@Composable
fun BookMessageItem(
    bookTitle: String,
    bookAuthor: String,
    bookCoverUrl: String?,
    isFromMe: Boolean,
    avatarUrl: String?
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (isFromMe) Arrangement.End else Arrangement.Start
    ) {
        if (!isFromMe && avatarUrl != null) {
            Box(
                modifier = Modifier
                    .size(32.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray)
            ) {
                AsyncImage(
                    model = avatarUrl,
                    contentDescription = "Avatar",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
        }
        
        Box(
            modifier = Modifier
                .widthIn(max = 200.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color.White)
                .clickable { /* TODO: Open book details */ }
        ) {
            Column(
                modifier = Modifier.padding(12.dp)
            ) {
                // Book Cover
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.LightGray)
                ) {
                    if (bookCoverUrl != null) {
                        AsyncImage(
                            model = bookCoverUrl,
                            contentDescription = "Book Cover",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(8.dp))
                
                // Book Info
                Text(
                    text = bookTitle,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    maxLines = 2
                )
                
                Text(
                    text = bookAuthor,
                    fontSize = 12.sp,
                    color = Color.Gray,
                    maxLines = 1
                )
            }
        }
    }
}

@Composable
fun ChatInputBar(
    messageText: String,
    onMessageChange: (String) -> Unit,
    onSendClick: () -> Unit,
    onVoiceClick: () -> Unit,
    onAttachClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Message Input
        TextField(
            value = messageText,
            onValueChange = onMessageChange,
            placeholder = {
                Text(
                    text = "Write your message",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            },
            modifier = Modifier
                .weight(1f)
                .height(48.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFF5F5F5),
                unfocusedContainerColor = Color(0xFFF5F5F5),
                disabledContainerColor = Color(0xFFF5F5F5),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(24.dp),
            singleLine = true
        )
        
        Spacer(modifier = Modifier.width(8.dp))
        
        // Voice Button
        IconButton(
            onClick = onVoiceClick,
            modifier = Modifier.size(40.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_mic),
                contentDescription = "Voice",
                tint = MainColor,
                modifier = Modifier.size(24.dp)
            )
        }
        
        // Send Button
        IconButton(
            onClick = onSendClick,
            modifier = Modifier.size(40.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_send),
                contentDescription = "Send",
                tint = MainColor,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

// Data Classes
sealed class ChatMessage {
    data class DateSeparator(val date: String) : ChatMessage()
    data class Text(
        val text: String,
        val isFromMe: Boolean,
        val time: String? = null
    ) : ChatMessage()
    data class Book(
        val bookTitle: String,
        val bookAuthor: String,
        val bookCoverUrl: String?,
        val isFromMe: Boolean
    ) : ChatMessage()
}

// Sample Data
val sampleMessages = listOf(
    ChatMessage.DateSeparator("yesterday"),
    ChatMessage.Text("Hai anaa, how are you?", false),
    ChatMessage.Text("im fine rain", true),
    ChatMessage.Text("rain, can you give me recomendation book? im so confuse what books can i read today", true),
    ChatMessage.Text("Hey Ana, I just finished reading this amazing book called filosofi teras by henry panampiring, and I think you would really enjoy it.", false),
    ChatMessage.Text("I've heard of Stoicism before, but I don't know much about it.", true),
    ChatMessage.Text("It's about the philosophy of Stoicism, which is a school of thought that teaches us how to live a happy and fulfilling life by focusing on what we can control and letting go of what we can't.", false),
    ChatMessage.Text("That sounds like good advice", true),
    ChatMessage.DateSeparator("today"),
    ChatMessage.Text("That's a good reminder", false),
    ChatMessage.Text("wait ana, i`ll send you that books", false),
    ChatMessage.Book(
        bookTitle = "FILOSOFI TERAS",
        bookAuthor = "HENRY MANAMPIRING",
        bookCoverUrl = null,
        isFromMe = false
    )
)

@Preview(showBackground = true)
@Composable
fun ChatScreenPreview() {
    ChatScreenContent()
}
