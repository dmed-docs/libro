package uz.luka.libro.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import uz.luka.libro.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

val CompactSmallTypography = Typography(
    // Display styles (eng katta matnlar)
    displayLarge = TextStyle(
        fontSize = 40.sp,
        fontFamily = FontFamily(listOf(Font(R.font.inter_bold))),
        color = Color(0xff262626)
    ),
    displayMedium = TextStyle(
        fontSize = 32.sp,
        fontFamily = FontFamily(listOf(Font(R.font.inter_bold))),
        color = Color(0xff262626)
    ),
    displaySmall = TextStyle(
        fontSize = 28.sp,
        fontFamily = FontFamily(listOf(Font(R.font.inter_bold))),
        color = Color(0xff262626)
    ),
    
    // Headline styles (sarlavhalar)
    headlineLarge = TextStyle(
        fontSize = 24.sp,
        fontFamily = FontFamily(listOf(Font(R.font.inter_semibold))),
        color = MediumGray
    ),
    headlineMedium = TextStyle(
        fontSize = 20.sp,
        fontFamily = FontFamily(listOf(Font(R.font.worksans_semibold))),
        color = Color(0xff262626)
    ),
    headlineSmall = TextStyle(
        fontSize = 18.sp,
        fontFamily = FontFamily(listOf(Font(R.font.inter_bold))),
        color = DarkGray
    ),
    
    // Title styles (kichik sarlavhalar)
    titleLarge = TextStyle(
        fontSize = 16.sp,
        fontFamily = FontFamily(listOf(Font(R.font.inter_bold))),
        color = Color(0xff262626)
    ),
    titleMedium = TextStyle(
        fontSize = 14.sp,
        fontFamily = FontFamily(listOf(Font(R.font.inter_semibold))),
        color = Color(0xff262626)
    ),
    titleSmall = TextStyle(
        fontSize = 12.sp,
        fontFamily = FontFamily(listOf(Font(R.font.inter_bold))),
        color = Color(0xff262626)
    ),
    
    // Body styles (asosiy matnlar)
    bodyLarge = TextStyle(
        fontSize = 14.sp,
        fontFamily = FontFamily(listOf(Font(R.font.inter_regular))),
        color = DarkGray
    ),
    bodyMedium = TextStyle(
        fontSize = 12.sp,
        fontFamily = FontFamily(listOf(Font(R.font.inter_regular))),
        color = DarkGray
    ),
    bodySmall = TextStyle(
        fontSize = 10.sp,
        fontFamily = FontFamily(listOf(Font(R.font.inter_regular))),
        color = DarkGray
    ),
    
    // Label styles (yorliqlar, tugmalar)
    labelLarge = TextStyle(
        fontSize = 14.sp,
        fontFamily = FontFamily(listOf(Font(R.font.publicsans_medium))),
        color = LightGray
    ),
    labelMedium = TextStyle(
        fontSize = 12.sp,
        fontFamily = FontFamily(listOf(Font(R.font.publicsans_medium))),
        color = LightGray
    ),
    labelSmall = TextStyle(
        fontSize = 10.sp,
        fontFamily = FontFamily(listOf(Font(R.font.publicsans_medium))),
        color = LightGray
    )
)

val CompactMediumTypography = Typography(
    // Display styles (eng katta matnlar)
    displayLarge = TextStyle(
        fontSize = 48.sp,
        fontFamily = FontFamily(listOf(Font(R.font.inter_bold))),
        color = Color(0xff262626)
    ),
    displayMedium = TextStyle(
        fontSize = 40.sp,
        fontFamily = FontFamily(listOf(Font(R.font.inter_bold))),
        color = Color(0xff262626)
    ),
    displaySmall = TextStyle(
        fontSize = 32.sp,
        fontFamily = FontFamily(listOf(Font(R.font.inter_bold))),
        color = Color(0xff262626)
    ),
    
    // Headline styles (sarlavhalar)
    headlineLarge = TextStyle(
        fontSize = 28.sp,
        fontFamily = FontFamily(listOf(Font(R.font.inter_semibold))),
        color = MediumGray
    ),
    headlineMedium = TextStyle(
        fontSize = 24.sp,
        fontFamily = FontFamily(listOf(Font(R.font.worksans_semibold))),
        color = Color(0xff262626)
    ),
    headlineSmall = TextStyle(
        fontSize = 20.sp,
        fontFamily = FontFamily(listOf(Font(R.font.inter_bold))),
        color = DarkGray
    ),
    
    // Title styles (kichik sarlavhalar)
    titleLarge = TextStyle(
        fontSize = 20.sp,
        fontFamily = FontFamily(listOf(Font(R.font.inter_bold))),
        color = Color(0xff262626)
    ),
    titleMedium = TextStyle(
        fontSize = 16.sp,
        fontFamily = FontFamily(listOf(Font(R.font.inter_semibold))),
        color = Color(0xff262626)
    ),
    titleSmall = TextStyle(
        fontSize = 14.sp,
        fontFamily = FontFamily(listOf(Font(R.font.inter_bold))),
        color = Color(0xff262626)
    ),
    
    // Body styles (asosiy matnlar)
    bodyLarge = TextStyle(
        fontSize = 16.sp,
        fontFamily = FontFamily(listOf(Font(R.font.inter_regular))),
        color = DarkGray
    ),
    bodyMedium = TextStyle(
        fontSize = 14.sp,
        fontFamily = FontFamily(listOf(Font(R.font.inter_regular))),
        color = DarkGray
    ),
    bodySmall = TextStyle(
        fontSize = 12.sp,
        fontFamily = FontFamily(listOf(Font(R.font.inter_regular))),
        color = DarkGray
    ),
    
    // Label styles (yorliqlar, tugmalar)
    labelLarge = TextStyle(
        fontSize = 16.sp,
        fontFamily = FontFamily(listOf(Font(R.font.publicsans_medium))),
        color = LightGray
    ),
    labelMedium = TextStyle(
        fontSize = 14.sp,
        fontFamily = FontFamily(listOf(Font(R.font.publicsans_medium))),
        color = LightGray
    ),
    labelSmall = TextStyle(
        fontSize = 12.sp,
        fontFamily = FontFamily(listOf(Font(R.font.publicsans_medium))),
        color = LightGray
    )
)

val CompactLargeTypography = Typography(
    // Display styles (eng katta matnlar)
    displayLarge = TextStyle(
        fontSize = 56.sp,
        fontFamily = FontFamily(listOf(Font(R.font.inter_bold))),
        color = Color(0xff262626)
    ),
    displayMedium = TextStyle(
        fontSize = 48.sp,
        fontFamily = FontFamily(listOf(Font(R.font.inter_bold))),
        color = Color(0xff262626)
    ),
    displaySmall = TextStyle(
        fontSize = 40.sp,
        fontFamily = FontFamily(listOf(Font(R.font.inter_bold))),
        color = Color(0xff262626)
    ),
    
    // Headline styles (sarlavhalar)
    headlineLarge = TextStyle(
        fontSize = 32.sp,
        fontFamily = FontFamily(listOf(Font(R.font.inter_semibold))),
        color = MediumGray
    ),
    headlineMedium = TextStyle(
        fontSize = 28.sp,
        fontFamily = FontFamily(listOf(Font(R.font.worksans_semibold))),
        color = Color(0xff262626)
    ),
    headlineSmall = TextStyle(
        fontSize = 24.sp,
        fontFamily = FontFamily(listOf(Font(R.font.inter_bold))),
        color = DarkGray
    ),
    
    // Title styles (kichik sarlavhalar)
    titleLarge = TextStyle(
        fontSize = 24.sp,
        fontFamily = FontFamily(listOf(Font(R.font.inter_bold))),
        color = Color(0xff262626)
    ),
    titleMedium = TextStyle(
        fontSize = 20.sp,
        fontFamily = FontFamily(listOf(Font(R.font.inter_semibold))),
        color = Color(0xff262626)
    ),
    titleSmall = TextStyle(
        fontSize = 18.sp,
        fontFamily = FontFamily(listOf(Font(R.font.inter_bold))),
        color = Color(0xff262626)
    ),
    
    // Body styles (asosiy matnlar)
    bodyLarge = TextStyle(
        fontSize = 18.sp,
        fontFamily = FontFamily(listOf(Font(R.font.inter_regular))),
        color = DarkGray
    ),
    bodyMedium = TextStyle(
        fontSize = 16.sp,
        fontFamily = FontFamily(listOf(Font(R.font.inter_regular))),
        color = DarkGray
    ),
    bodySmall = TextStyle(
        fontSize = 14.sp,
        fontFamily = FontFamily(listOf(Font(R.font.inter_regular))),
        color = DarkGray
    ),
    
    // Label styles (yorliqlar, tugmalar)
    labelLarge = TextStyle(
        fontSize = 18.sp,
        fontFamily = FontFamily(listOf(Font(R.font.publicsans_medium))),
        color = LightGray
    ),
    labelMedium = TextStyle(
        fontSize = 16.sp,
        fontFamily = FontFamily(listOf(Font(R.font.publicsans_medium))),
        color = LightGray
    ),
    labelSmall = TextStyle(
        fontSize = 14.sp,
        fontFamily = FontFamily(listOf(Font(R.font.publicsans_medium))),
        color = LightGray
    )
)


