package uz.luka.libro.presantation.screens.search

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
import uz.luka.libro.presantation.components.app.TextUi
import uz.luka.libro.presantation.screens.userprofile.UserProfileScreen
import uz.luka.libro.ui.theme.Black
import uz.luka.libro.ui.theme.DarkGray
import uz.luka.libro.ui.theme.LightGray
import uz.luka.libro.ui.theme.MainColor

class SearchUsersScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        
        SearchUsersScreenContent(
            onBackClick = { navigator.pop() },
            onUserClick = { userId ->
                navigator.push(UserProfileScreen(userId = userId))
            }
        )
    }
}

@Composable
fun SearchUsersScreenContent(
    searchResults: List<UserSearchResult> = sampleUsers,
    onBackClick: () -> Unit = {},
    onUserClick: (String) -> Unit = {}
) {
    var searchQuery by remember { mutableStateOf("") }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
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
            
            TextUi(
                text = "Search Users",
                color = Black,
                fontSize = 16,
                fontFamily = R.font.inter_bold
            )
        }
        
        // Search Field
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = "Search",
                        tint = LightGray,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    TextUi(
                        text = "Search by username",
                        color = DarkGray,
                        fontSize = 14,
                        fontFamily = R.font.inter_regular
                    )
                }
            },
            trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_close),
                        contentDescription = "Clear",
                        tint = DarkGray,
                        modifier = Modifier
                            .size(20.dp)
                            .clickable { searchQuery = "" }
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(52.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedBorderColor = LightGray,
                unfocusedBorderColor = LightGray,
                cursorColor = MainColor,
                focusedTextColor = Black,
                unfocusedTextColor = Black
            ),
            shape = RoundedCornerShape(12.dp),
            singleLine = true,
            textStyle = androidx.compose.ui.text.TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.inter_regular))
            )
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Search Results
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(searchResults) { user ->
                UserSearchItem(
                    user = user,
                    onClick = { onUserClick(user.userId) }
                )
            }
        }
    }
}

@Composable
fun UserSearchItem(
    user: UserSearchResult,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Avatar
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Color(0xFFF5F5F5))
        ) {
            if (user.avatarUrl != null) {
                AsyncImage(
                    model = user.avatarUrl,
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
            Row(verticalAlignment = Alignment.CenterVertically) {
                TextUi(
                    text = user.username,
                    color = Black,
                    fontSize = 14,
                    fontFamily = R.font.inter_bold
                )
                
                if (user.isVerified) {
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.ic_verified),
                        contentDescription = "Verified",
                        tint = MainColor,
                        modifier = Modifier.size(14.dp)
                    )
                }
            }
            
            if (user.fullName.isNotEmpty()) {
                Spacer(modifier = Modifier.height(2.dp))
                TextUi(
                    text = user.fullName,
                    color = DarkGray,
                    fontSize = 12,
                    fontFamily = R.font.inter_regular
                )
            }
        }
    }
}

// Data Classes
data class UserSearchResult(
    val userId: String,
    val username: String,
    val fullName: String,
    val avatarUrl: String?,
    val isVerified: Boolean
)

// Sample Data
val sampleUsers = listOf(
    UserSearchResult(
        userId = "1",
        username = "lukamodric10",
        fullName = "Luka Modric",
        avatarUrl = null,
        isVerified = true
    ),
    UserSearchResult(
        userId = "2",
        username = "lukavuskovic",
        fullName = "Luka Vušković",
        avatarUrl = null,
        isVerified = true
    ),
    UserSearchResult(
        userId = "3",
        username = "djlimak",
        fullName = "Kamil Lukasik",
        avatarUrl = null,
        isVerified = false
    ),
    UserSearchResult(
        userId = "4",
        username = "lukasfillio",
        fullName = "FILLIO DHENO",
        avatarUrl = null,
        isVerified = true
    ),
    UserSearchResult(
        userId = "5",
        username = "grohluk",
        fullName = "Lukas Groh",
        avatarUrl = null,
        isVerified = false
    )
)

@Preview(showBackground = true)
@Composable
fun SearchUsersScreenPreview() {
    SearchUsersScreenContent()
}
