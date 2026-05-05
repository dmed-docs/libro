package uz.luka.libro.presantation.screens.shareprofile

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import uz.luka.libro.R
import uz.luka.libro.presantation.components.app.TextUi
import uz.luka.libro.ui.theme.Black
import uz.luka.libro.ui.theme.DarkGray
import uz.luka.libro.ui.theme.MainColor
import uz.luka.libro.ui.theme.dimens

data class ShareProfileScreen(
    val username: String = "",
    val fullName: String = ""
) : Screen {
    @Composable
    override fun Content() {
        ShareProfileScreenContent(
            username = username,
            fullName = fullName
        )
    }
}

@Composable
fun EmojiPattern(emoji: String) {
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize()
    ) {
        val screenWidth = maxWidth.value
        val screenHeight = maxHeight.value
        
        // Create random positioned emojis
        val emojiCount = 50
        val random = remember { java.util.Random(42) } // Fixed seed for consistent layout
        
        repeat(emojiCount) { index ->
            val xPos = remember(index) { random.nextFloat() * screenWidth }
            val yPos = remember(index) { random.nextFloat() * screenHeight }
            val rotation = remember(index) { random.nextFloat() * 360f }
            val scale = remember(index) { 0.7f + random.nextFloat() * 0.6f } // 0.7 to 1.3
            
            Box(
                modifier = Modifier
                    .offset(x = xPos.dp, y = yPos.dp)
            ) {
                Text(
                    text = emoji,
                    fontSize = (50 * scale).sp,
                    modifier = Modifier
                        .alpha(0.15f)
                        .graphicsLayer {
                            rotationZ = rotation
                        }
                )
            }
        }
    }
}

@Composable
fun ShareProfileScreenContent(
    username: String = "xojiakbar_2329",
    fullName: String = "Xojiakbar"
) {
    val navigator = LocalNavigator.currentOrThrow
    val context = LocalContext.current
    
    var selectedPattern by remember { mutableStateOf("📕") }
    var showPatternSelector by remember { mutableStateOf(false) }
    
    val patterns = listOf(
        "📕", "📘", "📙","📖" , "📔" , "📚" , "😂"  , "😘" ,
        "😊", "❤️", "🔥", "⭐",
    )
    
    fun shareProfile() {
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, "Check out my Libro profile!")
            putExtra(
                Intent.EXTRA_TEXT,
                "Follow me on Libro: @$username\n\nDownload Libro app: https://libro.app"
            )
        }
        context.startActivity(Intent.createChooser(shareIntent, "Share Profile"))
    }
    
    fun copyLink() {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("Profile Link", "https://libro.app/@$username")
        clipboard.setPrimaryClip(clip)
        Toast.makeText(context, "Link copied to clipboard", Toast.LENGTH_SHORT).show()
    }
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Background Pattern
        EmojiPattern(emoji = selectedPattern)
        
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Top Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(MaterialTheme.dimens.topBarHeight)
                    .padding(horizontal = MaterialTheme.dimens.paddingMedium),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_close),
                    contentDescription = "Close",
                    tint = Color.Black,
                    modifier = Modifier
                        .size(28.dp)
                        .clickable { navigator.pop() }
                )
                
                Box(
                    modifier = Modifier
                        .border(2.dp , color = Color.Black , RoundedCornerShape(20.dp))
                        .background(Color.White)
                        .clickable { showPatternSelector = !showPatternSelector }
                        .padding(horizontal = 16.dp, vertical = 8.dp)

                ) {
                    TextUi(
                        text = "PATTERNS",
                        color = Color.Black,
                        fontSize = 14,
                        fontFamily = R.font.inter_semibold
                    )
                }
                
                Icon(
                    painter = painterResource(id = R.drawable.ic_qr_scan),
                    contentDescription = "Scan QR",
                    tint = Color.Black,
                    modifier = Modifier
                        .size(28.dp)
                        .clickable { /* TODO: Open QR Scanner */ }
                )
            }
            
            // Pattern Selector
            AnimatedVisibility(
                visible = showPatternSelector,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = MaterialTheme.dimens.paddingMedium)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.White.copy(alpha = 0.15f))
                        .padding(MaterialTheme.dimens.paddingMedium)
                ) {
                    Column {
                        TextUi(
                            text = "Select Pattern",
                            color = Color.White,
                            fontSize = 16,
                            fontFamily = R.font.inter_semibold
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        
                        // Pattern Grid
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            patterns.chunked(6).forEach { rowPatterns ->
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                                ) {
                                    rowPatterns.forEach { pattern ->
                                        Box(
                                            modifier = Modifier
                                                .size(48.dp)
                                                .clip(RoundedCornerShape(12.dp))
                                                .background(
                                                    if (pattern == selectedPattern) 
                                                        MainColor.copy(alpha = 0.3f)
                                                    else 
                                                        Color.White.copy(alpha = 0.1f)
                                                )
                                                .border(
                                                    2.dp,
                                                    if (pattern == selectedPattern) MainColor else Color.Transparent,
                                                    RoundedCornerShape(12.dp)
                                                )
                                                .clickable {
                                                    selectedPattern = pattern
                                                    showPatternSelector = false
                                                },
                                            contentAlignment = Alignment.Center
                                        ) {
                                            TextUi(
                                                text = pattern,
                                                fontSize = 24
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            
            Spacer(modifier = Modifier.weight(1f))
            
            // QR Code Card
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(24.dp))
                        .background(Color.White)
                        .padding(MaterialTheme.dimens.paddingExtraLarge),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Logo
                    Image(
                        painter = painterResource(id = R.drawable.splash_logo_second),
                        colorFilter = ColorFilter.tint(MainColor) ,
                        contentDescription = "Libro Logo",
                        modifier = Modifier
                            .width(120.dp)
                            .height(48.dp)
                    )
                    
                    Spacer(modifier = Modifier.height(MaterialTheme.dimens.spacingLarge))
                    
                    // QR Code Placeholder
                    Box(
                        modifier = Modifier
                            .size(200.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color(0xFFF5F5F5))
                            .border(2.dp, MainColor, RoundedCornerShape(16.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_qr_code),
                                contentDescription = "QR Code",
                                tint = MainColor,
                                modifier = Modifier.size(120.dp)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            TextUi(
                                text = "QR Code",
                                color = MainColor,
                                fontSize = 14,
                                fontFamily = R.font.inter_semibold
                            )
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(MaterialTheme.dimens.spacingLarge))
                    
                    // Username
                    TextUi(
                        text = "@$username",
                        color = MainColor,
                        fontSize = 20,
                        fontFamily = R.font.inter_bold
                    )
                }
            }
            
            Spacer(modifier = Modifier.weight(1f))
            
            // Action Buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
                    .padding(bottom = 48.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                // Share Button
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable { shareProfile() }
                ) {
                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .border(width = 2.dp , color = Color.Black , CircleShape)
                            .background(Color.White)
                            ,
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_share),
                            contentDescription = "Share",
                            tint = Color.Black,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    TextUi(
                        text = "Share",
                        color = Color.Black,
                        fontSize = 12,
                        fontFamily = R.font.inter_medium
                    )
                }
                
                // Copy Link Button
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable { copyLink() }
                ) {
                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .border(width = 2.dp , color = Color.Black , CircleShape)
                            .background(Color.White)
                            ,
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_link),
                            contentDescription = "Copy Link",
                            tint = Color.Black,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    TextUi(
                        text = "Copy Link",
                        color = Color.Black,
                        fontSize = 12,
                        fontFamily = R.font.inter_medium
                    )
                }
                
                // Download Button
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable { /* TODO: Download QR */ }
                ) {
                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .border(width = 2.dp , color = Color.Black , shape = CircleShape)
                            .background(Color.White)
                            ,
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_download),
                            contentDescription = "Download",
                            tint = Color.Black,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    TextUi(
                        text = "Download",
                        color = Color.Black,
                        fontSize = 12,
                        fontFamily = R.font.inter_medium
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShareProfileScreenPreview() {
    ShareProfileScreenContent()
}
