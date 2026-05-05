package uz.luka.libro.ui.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimens(
    // Padding
    val paddingTiny: Dp = 0.dp,
    val paddingSmall: Dp = 0.dp,
    val paddingMedium: Dp = 0.dp,
    val paddingLarge: Dp = 0.dp,
    val paddingExtraLarge: Dp = 0.dp,
    
    // Spacing
    val spacingTiny: Dp = 0.dp,
    val spacingSmall: Dp = 0.dp,
    val spacingMedium: Dp = 0.dp,
    val spacingLarge: Dp = 0.dp,
    val spacingExtraLarge: Dp = 0.dp,
    
    // Component Heights
    val buttonHeightSmall: Dp = 0.dp,
    val buttonHeightMedium: Dp = 0.dp,
    val buttonHeightLarge: Dp = 0.dp,
    val textFieldHeight: Dp = 0.dp,
    val searchBarHeight: Dp = 0.dp,
    val topBarHeight: Dp = 0.dp,
    val bottomNavHeight: Dp = 0.dp,
    val bottomNavFabSize: Dp = 0.dp,
    
    // Component Widths
    val buttonWidthSmall: Dp = 0.dp,
    val cardWidthSmall: Dp = 0.dp,
    val imageWidthMedium: Dp = 0.dp,
    
    // Icon Sizes
    val iconSizeSmall: Dp = 0.dp,
    val iconSizeMedium: Dp = 0.dp,
    val iconSizeLarge: Dp = 0.dp,
    
    // Corner Radius
    val cornerRadiusSmall: Dp = 0.dp,
    val cornerRadiusMedium: Dp = 0.dp,
    val cornerRadiusLarge: Dp = 0.dp,
    val cornerRadiusExtraLarge: Dp = 0.dp,
    val cornerRadiusFull: Dp = 0.dp,
    
    // Specific Sizes
    val logoSize: Dp = 0.dp,
    val categoryCardSize: Dp = 0.dp,
    val categoryCardTextHeight: Dp = 0.dp,
    val recommendationCardTextHeight: Dp = 0.dp,
    
    // Legacy (for backward compatibility)
    val small1: Dp = 0.dp,
    val small2: Dp = 0.dp,
    val small3: Dp = 0.dp,
    val medium1: Dp = 0.dp,
    val medium2: Dp = 0.dp,
    val medium3: Dp = 0.dp,
    val large: Dp = 0.dp,
)

val CompactSmallDimens = Dimens(
    // Padding
    paddingTiny = 4.dp,
    paddingSmall = 8.dp,
    paddingMedium = 12.dp,
    paddingLarge = 16.dp,
    paddingExtraLarge = 20.dp,
    
    // Spacing
    spacingTiny = 4.dp,
    spacingSmall = 6.dp,
    spacingMedium = 10.dp,
    spacingLarge = 16.dp,
    spacingExtraLarge = 24.dp,
    
    // Component Heights
    buttonHeightSmall = 30.dp,
    buttonHeightMedium = 32.dp,
    buttonHeightLarge = 36.dp,
    textFieldHeight = 48.dp,
    searchBarHeight = 36.dp,
    topBarHeight = 100.dp,
    bottomNavHeight = 60.dp,
    bottomNavFabSize = 60.dp,
    
    // Component Widths
    buttonWidthSmall = 90.dp,
    cardWidthSmall = 38.dp,
    imageWidthMedium = 260.dp,
    
    // Icon Sizes
    iconSizeSmall = 14.dp,
    iconSizeMedium = 20.dp,
    iconSizeLarge = 28.dp,
    
    // Corner Radius
    cornerRadiusSmall = 6.dp,
    cornerRadiusMedium = 12.dp,
    cornerRadiusLarge = 18.dp,
    cornerRadiusExtraLarge = 24.dp,
    cornerRadiusFull = 50.dp,
    
    // Specific Sizes
    logoSize = 36.dp,
    categoryCardSize = 38.dp,
    categoryCardTextHeight = 18.dp,
    recommendationCardTextHeight = 26.dp,
    
    // Legacy
    small1 = 5.dp,
    small2 = 6.dp,
    small3 = 8.dp,
    medium1 = 25.dp,
    medium2 = 26.dp,
    medium3 = 30.dp,
    large = 45.dp,
)

val CompactMediumDimens = Dimens(
    // Padding
    paddingTiny = 5.dp,
    paddingSmall = 10.dp,
    paddingMedium = 16.dp,
    paddingLarge = 20.dp,
    paddingExtraLarge = 26.dp,
    
    // Spacing
    spacingTiny = 4.dp,
    spacingSmall = 8.dp,
    spacingMedium = 16.dp,
    spacingLarge = 26.dp,
    spacingExtraLarge = 35.dp,
    
    // Component Heights
    buttonHeightSmall = 32.dp,
    buttonHeightMedium = 36.dp,
    buttonHeightLarge = 42.dp,
    textFieldHeight = 53.dp,
    searchBarHeight = 40.dp,
    topBarHeight = 120.dp,
    bottomNavHeight = 70.dp,
    bottomNavFabSize = 70.dp,
    
    // Component Widths
    buttonWidthSmall = 100.dp,
    cardWidthSmall = 43.dp,
    imageWidthMedium = 300.dp,
    
    // Icon Sizes
    iconSizeSmall = 16.dp,
    iconSizeMedium = 22.dp,
    iconSizeLarge = 32.dp,
    
    // Corner Radius
    cornerRadiusSmall = 8.dp,
    cornerRadiusMedium = 16.dp,
    cornerRadiusLarge = 22.dp,
    cornerRadiusExtraLarge = 28.dp,
    cornerRadiusFull = 50.dp,
    
    // Specific Sizes
    logoSize = 42.dp,
    categoryCardSize = 43.dp,
    categoryCardTextHeight = 20.dp,
    recommendationCardTextHeight = 30.dp,
    
    // Legacy
    small1 = 8.dp,
    small2 = 13.dp,
    small3 = 16.dp,
    medium1 = 25.dp,
    medium2 = 30.dp,
    medium3 = 35.dp,
    large = 65.dp,
)

val CompactDimens = Dimens(
    // Padding
    paddingTiny = 6.dp,
    paddingSmall = 12.dp,
    paddingMedium = 18.dp,
    paddingLarge = 24.dp,
    paddingExtraLarge = 32.dp,
    
    // Spacing
    spacingTiny = 5.dp,
    spacingSmall = 10.dp,
    spacingMedium = 20.dp,
    spacingLarge = 32.dp,
    spacingExtraLarge = 45.dp,
    
    // Component Heights
    buttonHeightSmall = 34.dp,
    buttonHeightMedium = 40.dp,
    buttonHeightLarge = 48.dp,
    textFieldHeight = 58.dp,
    searchBarHeight = 44.dp,
    topBarHeight = 130.dp,
    bottomNavHeight = 75.dp,
    bottomNavFabSize = 75.dp,
    
    // Component Widths
    buttonWidthSmall = 110.dp,
    cardWidthSmall = 48.dp,
    imageWidthMedium = 340.dp,
    
    // Icon Sizes
    iconSizeSmall = 18.dp,
    iconSizeMedium = 24.dp,
    iconSizeLarge = 36.dp,
    
    // Corner Radius
    cornerRadiusSmall = 10.dp,
    cornerRadiusMedium = 18.dp,
    cornerRadiusLarge = 24.dp,
    cornerRadiusExtraLarge = 32.dp,
    cornerRadiusFull = 50.dp,
    
    // Specific Sizes
    logoSize = 48.dp,
    categoryCardSize = 48.dp,
    categoryCardTextHeight = 22.dp,
    recommendationCardTextHeight = 32.dp,
    
    // Legacy
    small1 = 10.dp,
    small2 = 15.dp,
    small3 = 16.dp,
    medium1 = 25.dp,
    medium2 = 36.dp,
    medium3 = 58.dp,
    large = 110.dp,
)

val MediumDimens = Dimens(
    // Padding
    paddingTiny = 8.dp,
    paddingSmall = 14.dp,
    paddingMedium = 20.dp,
    paddingLarge = 28.dp,
    paddingExtraLarge = 36.dp,
    
    // Spacing
    spacingTiny = 6.dp,
    spacingSmall = 12.dp,
    spacingMedium = 24.dp,
    spacingLarge = 36.dp,
    spacingExtraLarge = 50.dp,
    
    // Component Heights
    buttonHeightSmall = 36.dp,
    buttonHeightMedium = 44.dp,
    buttonHeightLarge = 52.dp,
    textFieldHeight = 62.dp,
    searchBarHeight = 48.dp,
    topBarHeight = 140.dp,
    bottomNavHeight = 80.dp,
    bottomNavFabSize = 80.dp,
    
    // Component Widths
    buttonWidthSmall = 120.dp,
    cardWidthSmall = 52.dp,
    imageWidthMedium = 380.dp,
    
    // Icon Sizes
    iconSizeSmall = 20.dp,
    iconSizeMedium = 26.dp,
    iconSizeLarge = 40.dp,
    
    // Corner Radius
    cornerRadiusSmall = 12.dp,
    cornerRadiusMedium = 20.dp,
    cornerRadiusLarge = 26.dp,
    cornerRadiusExtraLarge = 36.dp,
    cornerRadiusFull = 50.dp,
    
    // Specific Sizes
    logoSize = 55.dp,
    categoryCardSize = 52.dp,
    categoryCardTextHeight = 24.dp,
    recommendationCardTextHeight = 34.dp,
    
    // Legacy
    small1 = 10.dp,
    small2 = 15.dp,
    small3 = 20.dp,
    medium1 = 30.dp,
    medium2 = 36.dp,
    medium3 = 40.dp,
    large = 110.dp,
)

val ExpandedDimens = Dimens(
    // Padding
    paddingTiny = 10.dp,
    paddingSmall = 16.dp,
    paddingMedium = 24.dp,
    paddingLarge = 32.dp,
    paddingExtraLarge = 42.dp,
    
    // Spacing
    spacingTiny = 8.dp,
    spacingSmall = 14.dp,
    spacingMedium = 28.dp,
    spacingLarge = 42.dp,
    spacingExtraLarge = 60.dp,
    
    // Component Heights
    buttonHeightSmall = 40.dp,
    buttonHeightMedium = 48.dp,
    buttonHeightLarge = 58.dp,
    textFieldHeight = 68.dp,
    searchBarHeight = 52.dp,
    topBarHeight = 160.dp,
    bottomNavHeight = 90.dp,
    bottomNavFabSize = 90.dp,
    
    // Component Widths
    buttonWidthSmall = 130.dp,
    cardWidthSmall = 58.dp,
    imageWidthMedium = 420.dp,
    
    // Icon Sizes
    iconSizeSmall = 22.dp,
    iconSizeMedium = 28.dp,
    iconSizeLarge = 44.dp,
    
    // Corner Radius
    cornerRadiusSmall = 14.dp,
    cornerRadiusMedium = 22.dp,
    cornerRadiusLarge = 30.dp,
    cornerRadiusExtraLarge = 40.dp,
    cornerRadiusFull = 50.dp,
    
    // Specific Sizes
    logoSize = 72.dp,
    categoryCardSize = 58.dp,
    categoryCardTextHeight = 28.dp,
    recommendationCardTextHeight = 38.dp,
    
    // Legacy
    small1 = 15.dp,
    small2 = 20.dp,
    small3 = 25.dp,
    medium1 = 35.dp,
    medium2 = 41.dp,
    medium3 = 45.dp,
    large = 130.dp,
)