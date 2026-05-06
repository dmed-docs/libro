package uz.luka.libro.presantation.screens.userprofile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil3.compose.AsyncImage
import uz.luka.libro.R
import uz.luka.libro.presantation.components.app.ButtonUi
import uz.luka.libro.presantation.components.app.TextUi
import uz.luka.libro.presantation.screens.chat.ChatScreen
import uz.luka.libro.ui.theme.Black
import uz.luka.libro.ui.theme.DarkGray
import uz.luka.libro.ui.theme.LightGray
import uz.luka.libro.ui.theme.MainColor

data class UserProfileScreen(
    val userId: String
) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        
        // TODO: Load actual user data based on userId
        val username = "lukamodric10"
        val avatarUrl: String? = null
        
        UserProfileScreenContent(
            onBackClick = { navigator.pop() },
            onMessageClick = {
                navigator.push(
                    ChatScreen(
                        userId = userId,
                        username = username,
                        avatarUrl = avatarUrl
                    )
                )
            },
            onFollowClick = {
                // TODO: Follow/Unfollow logic
            }
        )
    }
}

@Composable
fun UserProfileScreenContent(
    username: String = "lukamodric10",
    fullName: String = "Luka Modric",
    bio: String = "Book lover and avid reader\nAlways looking for new recommendations",
    websiteUrl: String = "lukamodric10.com",
    readsCount: Int = 156,
    followersCount: Int = 1240,
    followingCount: Int = 583,
    avatarUrl: String? = null,
    isVerified: Boolean = true,
    isFollowing: Boolean = false,
    onBackClick: () -> Unit = {},
    onMessageClick: () -> Unit = {},
    onFollowClick: () -> Unit = {},
    onMenuClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
    ) {
        // Top Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .size(24.dp)
                    .clickable { onBackClick() },
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "Back"
            )
            
            Spacer(modifier = Modifier.width(12.dp))
            
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                TextUi(
                    text = username,
                    color = Black,
                    fontSize = 16,
                    fontFamily = R.font.inter_bold
                )
                
                if (isVerified) {
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.ic_verified),
                        contentDescription = "Verified",
                        tint = MainColor,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
            
            Image(
                modifier = Modifier
                    .size(18.dp)
                    .clickable { onMenuClick() },
                painter = painterResource(id = R.drawable.ic_menu),
                contentDescription = "Menu"
            )
        }
        
        // Profile Content
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            
            // Avatar and Stats
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Avatar
                Box(
                    modifier = Modifier
                        .size(72.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFF5F5F5))
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
                
                Spacer(modifier = Modifier.weight(1f))
                
                // Stats
                Row(
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    StatItem(count = readsCount.toString(), label = "Reads")
                    StatItem(count = followersCount.toString(), label = "Followers")
                    StatItem(count = followingCount.toString(), label = "Following")
                }
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // Name
            if (fullName.isNotEmpty()) {
                TextUi(
                    text = fullName,
                    color = Black,
                    fontSize = 12,
                    fontFamily = R.font.inter_bold
                )
                Spacer(modifier = Modifier.height(4.dp))
            }
            
            // Bio
            if (bio.isNotEmpty()) {
                TextUi(
                    text = bio,
                    color = DarkGray,
                    fontSize = 12,
                    fontFamily = R.font.inter_regular
                )
                Spacer(modifier = Modifier.height(4.dp))
            }
            
            // Website
            if (websiteUrl.isNotEmpty()) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_link),
                        contentDescription = "Link",
                        tint = MainColor,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    TextUi(
                        text = websiteUrl,
                        color = MainColor,
                        fontSize = 12,
                        fontFamily = R.font.inter_regular
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // Action Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Follow/Following Button
                ButtonUi(
                    modifier = Modifier
                        .weight(1f)
                        .height(32.dp),
                    isModifier = true,
                    text = if (isFollowing) "Following" else "Follow",
                    textSize = 12,
                    fontFamily = R.font.inter_bold,
                    cornerRadius = 8,
                    backgroundColor = if (isFollowing) Color.White else MainColor,
                    textColor = if (isFollowing) Black else Color.White,
                    borderSize = if (isFollowing) 1 else 0,
                    borderColor = LightGray,
                    onClick = onFollowClick
                )
                
                // Message Button
                ButtonUi(
                    modifier = Modifier
                        .weight(1f)
                        .height(32.dp),
                    isModifier = true,
                    text = "Message",
                    textSize = 12,
                    fontFamily = R.font.inter_bold,
                    cornerRadius = 8,
                    backgroundColor = Color.White,
                    textColor = Black,
                    borderSize = 1,
                    borderColor = LightGray,
                    onClick = onMessageClick
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Divider(color = LightGray, thickness = 1.dp)
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Books Grid (placeholder)
            TextUi(
                text = "Saved Books",
                color = Black,
                fontSize = 14,
                fontFamily = R.font.inter_bold
            )
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // TODO: Add books grid here
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFFF5F5F5)),
                contentAlignment = Alignment.Center
            ) {
                TextUi(
                    text = "No books yet",
                    color = DarkGray,
                    fontSize = 12,
                    fontFamily = R.font.inter_regular
                )
            }
        }
    }
}

@Composable
fun StatItem(count: String, label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextUi(
            text = count,
            color = Black,
            fontSize = 14,
            fontFamily = R.font.inter_bold
        )
        TextUi(
            text = label,
            color = DarkGray,
            fontSize = 10,
            fontFamily = R.font.inter_regular
        )
    }
}

@Preview(showBackground = true)
@Composable
fun UserProfileScreenPreview() {
    UserProfileScreenContent()
}
