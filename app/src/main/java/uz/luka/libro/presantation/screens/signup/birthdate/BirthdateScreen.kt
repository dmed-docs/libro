package uz.luka.libro.presantation.screens.signup.birthdate

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import uz.luka.libro.R
import uz.luka.libro.ui.theme.MainColor
import uz.luka.libro.ui.theme.dimens

class BirthdateScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = getViewModel<BirthdateViewModel>()
        BirthdateScreenContent(
            uiState = viewModel.uiState.collectAsState().value,
            onEvent = viewModel::onEventDispatcher
        )
    }
}

@Composable
fun BirthdateScreenContent(
    uiState: BirthdateContract.UiState = BirthdateContract.UiState(),
    onEvent: (BirthdateContract.Intent) -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(MaterialTheme.dimens.paddingLarge)
        ) {
            // Back button
            IconButton(onClick = { onEvent(BirthdateContract.Intent.OnBackClick) }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_user),
                    contentDescription = "Back",
                    tint = MainColor,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.height(MaterialTheme.dimens.spacingLarge))

            // Title
            Text(
                text = "Add your birthday",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontFamily = FontFamily(listOf(Font(R.font.roboto_bold)))
                )
            )

            Spacer(modifier = Modifier.height(MaterialTheme.dimens.spacingSmall))

            // Description
            Text(
                text = "This won't be part of your public profile.",
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color.Gray,
                    fontFamily = FontFamily(listOf(Font(R.font.inter_regular)))
                )
            )

            Spacer(modifier = Modifier.height(MaterialTheme.dimens.spacingExtraLarge))

            // Birthdate display (clickable)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(Color(0xFFF5F5F5), RoundedCornerShape(8.dp))
                    .clickable { /* TODO: Show date picker */ },
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = if (uiState.birthdate.isEmpty()) "Select date" else uiState.birthdate,
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = if (uiState.birthdate.isEmpty()) Color.Gray else Color.Black,
                        fontFamily = FontFamily(listOf(Font(R.font.inter_regular)))
                    ),
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }

            Spacer(modifier = Modifier.height(MaterialTheme.dimens.spacingMedium))

            // Test hint
            Text(
                text = "Test: Any date will work",
                style = TextStyle(
                    fontSize = 12.sp,
                    color = Color.Gray,
                    fontFamily = FontFamily(listOf(Font(R.font.inter_regular)))
                )
            )

            // Quick test button
            TextButton(
                onClick = { onEvent(BirthdateContract.Intent.OnBirthdateChange("July 4, 2003")) }
            ) {
                Text(
                    text = "Use test date (July 4, 2003)",
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = MainColor,
                        fontFamily = FontFamily(listOf(Font(R.font.inter_semibold)))
                    )
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // Next button
            Button(
                onClick = { onEvent(BirthdateContract.Intent.OnNextClick) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(MaterialTheme.dimens.buttonHeightLarge),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MainColor
                ),
                shape = RoundedCornerShape(MaterialTheme.dimens.cornerRadiusMedium),
                enabled = uiState.birthdate.isNotEmpty()
            ) {
                Text(
                    text = "Next",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontFamily = FontFamily(listOf(Font(R.font.worksans_semibold)))
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BirthdateScreenPreview() {
    BirthdateScreenContent()
}

