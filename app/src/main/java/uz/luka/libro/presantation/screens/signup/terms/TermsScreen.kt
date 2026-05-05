package uz.luka.libro.presantation.screens.signup.terms

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import uz.luka.libro.ui.theme.MainColor
import uz.luka.libro.ui.theme.dimens

class TermsScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel: TermsViewModel = getViewModel()
        val uiState = viewModel.uiState.collectAsState().value
        
        TermsScreenContent(
            onAcceptClick = { viewModel.onEventDispatcher(TermsContract.Intent.AcceptTerms) },
            onBackClick = { viewModel.onEventDispatcher(TermsContract.Intent.OnBackClick) }
        )
    }
}

@Composable
fun TermsScreenContent(
    onAcceptClick: () -> Unit,
    onBackClick: () -> Unit
) {
    val scrollState = rememberScrollState()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(MaterialTheme.dimens.paddingLarge)
    ) {
        // Back button
        IconButton(
            onClick = onBackClick,
            modifier = Modifier.size(MaterialTheme.dimens.iconSizeLarge)
        ) {
            Text(
                text = "←",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.Black
            )
        }
        
        Spacer(modifier = Modifier.height(MaterialTheme.dimens.spacingMedium))
        
        // Title
        Text(
            text = "Shartlar va Qoidalar",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold
            ),
            color = Color.Black,
            modifier = Modifier.padding(bottom = MaterialTheme.dimens.spacingMedium)
        )
        
        // Scrollable terms content
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(scrollState)
        ) {
            // Test content - Libro ilovasi uchun shartlar
            TermsSection(
                title = "1. Umumiy qoidalar",
                content = "Libro ilovasidan foydalanish orqali siz quyidagi shartlarga rozilik bildirasiz. Iltimos, ilovadan foydalanishdan oldin barcha shartlarni diqqat bilan o'qib chiqing."
            )
            
            TermsSection(
                title = "2. Foydalanuvchi ma'lumotlari",
                content = "Siz tomonidan taqdim etilgan barcha ma'lumotlar maxfiy saqlanadi va uchinchi shaxslarga berilmaydi. Biz faqat ilovaning ishlashini yaxshilash uchun ma'lumotlardan foydalanamiz."
            )
            
            TermsSection(
                title = "3. Kitoblar va mualliflik huquqi",
                content = "Libro ilovasida mavjud barcha kitoblar mualliflik huquqi bilan himoyalangan. Kitoblarni nusxalash, tarqatish yoki tijoriy maqsadlarda foydalanish qat'iyan man etiladi."
            )
            
            TermsSection(
                title = "4. Foydalanuvchi mas'uliyati",
                content = "Siz o'z akkauntingiz xavfsizligi uchun javobgarsiz. Parolingizni boshqalar bilan bo'lishmaslik va xavfsiz saqlash tavsiya etiladi."
            )
            
            TermsSection(
                title = "5. Xizmat shartlari",
                content = "Libro jamoasi ilovaning uzluksiz ishlashini ta'minlashga harakat qiladi, lekin texnik nosozliklar yuzaga kelishi mumkin. Bunday holatlarda biz tezkor tarzda muammolarni hal qilishga intilamiz."
            )
            
            TermsSection(
                title = "6. O'zgarishlar",
                content = "Libro shartlar va qoidalarni istalgan vaqtda o'zgartirish huquqini o'zida saqlab qoladi. O'zgarishlar haqida foydalanuvchilar xabardor qilinadi."
            )
            
            Spacer(modifier = Modifier.height(MaterialTheme.dimens.spacingLarge))
        }
        
        // Accept button
        Button(
            onClick = onAcceptClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(MaterialTheme.dimens.buttonHeightLarge),
            colors = ButtonDefaults.buttonColors(
                containerColor = MainColor
            ),
            shape = RoundedCornerShape(MaterialTheme.dimens.cornerRadiusSmall)
        ) {
            Text(
                text = "Qabul qilaman",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                color = Color.White
            )
        }
        
        Spacer(modifier = Modifier.height(MaterialTheme.dimens.spacingSmall))
    }
}

@Composable
fun TermsSection(
    title: String,
    content: String
) {
    Column(
        modifier = Modifier.padding(bottom = MaterialTheme.dimens.spacingMedium)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold
            ),
            color = Color.Black,
            modifier = Modifier.padding(bottom = MaterialTheme.dimens.spacingSmall)
        )
        
        Text(
            text = content,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.DarkGray,
            lineHeight = MaterialTheme.typography.bodyMedium.lineHeight
        )
    }
}
