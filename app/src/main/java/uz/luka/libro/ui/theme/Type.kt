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
    titleSmall = TextStyle(
        fontSize = 12.sp ,
        fontFamily = FontFamily(listOf(Font(R.font.inter_bold))) ,
        color = Color(0xff262626)
    ) ,
    headlineSmall = TextStyle(
        color = DarkGray ,
        fontFamily = FontFamily(listOf(Font(R.font.inter_bold))) ,
        fontSize = 10.sp
    ),
    headlineMedium = TextStyle(
        fontSize = 12.sp ,
        fontFamily = FontFamily(listOf(Font(R.font.worksans_semibold))) ,
        color = Color(0xff262626)
    ) ,
    headlineLarge = TextStyle(
        color = MediumGray ,
        fontFamily = FontFamily(listOf(Font(R.font.inter_semibold))) ,
        fontSize = 10.sp
    ) ,
    labelMedium = TextStyle(
        fontFamily = FontFamily(listOf(Font(R.font.publicsans_medium))) ,
        color = LightGray ,
        fontSize = 12.sp
    )
)

val CompactMediumTypography = Typography(
    titleSmall = TextStyle(
        fontSize = 16.sp ,
        fontFamily = FontFamily(listOf(Font(R.font.inter_bold))) ,
        color = Color(0xff262626)
    ) ,
    headlineSmall = TextStyle(
        color = DarkGray ,
        fontFamily = FontFamily(listOf(Font(R.font.inter_bold))) ,
        fontSize = 14.sp
    ),
    headlineMedium = TextStyle(
        fontSize = 16.sp ,
        fontFamily = FontFamily(listOf(Font(R.font.worksans_semibold))) ,
        color = Color(0xff262626)
    ) ,
    headlineLarge = TextStyle(
        color = MediumGray ,
        fontFamily = FontFamily(listOf(Font(R.font.inter_semibold))) ,
        fontSize = 14.sp
    ) ,
    labelMedium = TextStyle(
        fontFamily = FontFamily(listOf(Font(R.font.publicsans_medium))) ,
        color = LightGray ,
        fontSize = 14.sp
    )
)

val CompactLargeTypography = Typography(
    titleSmall = TextStyle(
        fontSize = 20.sp ,
        fontFamily = FontFamily(listOf(Font(R.font.inter_bold))) ,
        color = Color(0xff262626)
    ) ,
    headlineSmall = TextStyle(
        color = DarkGray ,
        fontFamily = FontFamily(listOf(Font(R.font.inter_bold))) ,
        fontSize = 20.sp
    ),
    headlineMedium = TextStyle(
        fontSize = 20.sp ,
        fontFamily = FontFamily(listOf(Font(R.font.worksans_semibold))) ,
        color = Color(0xff262626)
    ) ,
    headlineLarge = TextStyle(
        color = MediumGray ,
        fontFamily = FontFamily(listOf(Font(R.font.inter_semibold))) ,
        fontSize = 18.sp
    ) ,
    labelMedium = TextStyle(
        fontFamily = FontFamily(listOf(Font(R.font.publicsans_medium))) ,
        color = LightGray ,
        fontSize = 18.sp
    )
)


