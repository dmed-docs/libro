package uz.luka.libro.presantation.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import uz.luka.libro.R
import uz.luka.libro.ui.theme.MainColor
import uz.luka.libro.ui.theme.dimens

class SettingsScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        
        SettingsScreenContent(
            onBackClick = { navigator.pop() }
        )
    }
}

@Composable
fun SettingsScreenContent(
    onBackClick: () -> Unit
) {
    val scrollState = rememberScrollState()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Top Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.dimens.paddingMedium),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClick) {
                Text(
                    text = "←",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.Black
                )
            }
            
            Text(
                text = "Sozlamalar",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = Color.Black
            )
        }
        
        Divider(color = Color(0xFFE0E0E0))
        
        // Settings List
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            // Akkaunt
            SettingsItem(
                icon = R.drawable.ic_profile,
                title = "Profil sozlamalari",
                onClick = { /* TODO */ }
            )
            
            SettingsItem(
                icon = R.drawable.ic_password,
                title = "Parolni o'zgartirish",
                onClick = { /* TODO */ }
            )
            
            SettingsItem(
                icon = R.drawable.ic_user,
                title = "Maxfiylik va xavfsizlik",
                onClick = { /* TODO */ }
            )
            
            Divider(color = Color(0xFFF0F0F0), thickness = 8.dp)
            
            // O'qish
            SettingsItem(
                icon = R.drawable.ic_user,
                title = "O'qish sozlamalari",
                subtitle = "Shrift, rang, yorug'lik",
                onClick = { /* TODO */ }
            )
            
            SettingsItem(
                icon = R.drawable.ic_user,
                title = "Yuklab olingan kitoblar",
                onClick = { /* TODO */ }
            )
            
            Divider(color = Color(0xFFF0F0F0), thickness = 8.dp)
            
            // Bildirishnomalar
            var pushEnabled by remember { mutableStateOf(true) }
            var emailEnabled by remember { mutableStateOf(false) }
            
            SettingsItemWithSwitch(
                icon = R.drawable.ic_user,
                title = "Push bildirishnomalar",
                checked = pushEnabled,
                onCheckedChange = { pushEnabled = it }
            )
            
            SettingsItemWithSwitch(
                icon = R.drawable.ic_user,
                title = "Email bildirishnomalar",
                checked = emailEnabled,
                onCheckedChange = { emailEnabled = it }
            )
            
            Divider(color = Color(0xFFF0F0F0), thickness = 8.dp)
            
            // Ilova
            SettingsItem(
                icon = R.drawable.ic_user,
                title = "Til",
                subtitle = "O'zbekcha",
                onClick = { /* TODO */ }
            )
            
            SettingsItem(
                icon = R.drawable.ic_user,
                title = "Yordam va qo'llab-quvvatlash",
                onClick = { /* TODO */ }
            )
            
            SettingsItem(
                icon = R.drawable.ic_user,
                title = "Ilova haqida",
                subtitle = "Versiya 1.0.0",
                onClick = { /* TODO */ }
            )
            
            Divider(color = Color(0xFFF0F0F0), thickness = 8.dp)
            
            // Chiqish
            SettingsItem(
                icon = R.drawable.ic_user,
                title = "Chiqish",
                titleColor = MainColor,
                showArrow = false,
                onClick = { /* TODO: Logout */ }
            )
            
            Spacer(modifier = Modifier.height(MaterialTheme.dimens.spacingExtraLarge))
        }
    }
}

@Composable
fun SettingsItem(
    icon: Int,
    title: String,
    subtitle: String? = null,
    titleColor: Color = Color.Black,
    showArrow: Boolean = true,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(
                horizontal = MaterialTheme.dimens.paddingMedium,
                vertical = 16.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = title,
            tint = Color.Gray,
            modifier = Modifier.size(24.dp)
        )
        
        Spacer(modifier = Modifier.width(16.dp))
        
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                color = titleColor
            )
            
            if (subtitle != null) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
        }
        
        if (showArrow) {
            Text(
                text = "›",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.LightGray
            )
        }
    }
}

@Composable
fun SettingsItemWithSwitch(
    icon: Int,
    title: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = MaterialTheme.dimens.paddingMedium,
                vertical = 16.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = title,
            tint = Color.Gray,
            modifier = Modifier.size(24.dp)
        )
        
        Spacer(modifier = Modifier.width(16.dp))
        
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Black,
            modifier = Modifier.weight(1f)
        )
        
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = MainColor,
                uncheckedThumbColor = Color.White,
                uncheckedTrackColor = Color.LightGray
            )
        )
    }
}
