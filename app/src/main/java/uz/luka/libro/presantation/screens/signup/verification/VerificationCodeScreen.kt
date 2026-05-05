package uz.luka.libro.presantation.screens.signup.verification

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import uz.luka.libro.R
import uz.luka.libro.ui.theme.MainColor
import uz.luka.libro.ui.theme.dimens

data class VerificationCodeScreen(val phoneOrEmail: String) : Screen {
    @Composable
    override fun Content() {
        val viewModel = getViewModel<VerificationCodeViewModel>()
        VerificationCodeScreenContent(
            phoneOrEmail = phoneOrEmail,
            uiState = viewModel.uiState.collectAsState().value,
            onEvent = viewModel::onEventDispatcher
        )
    }
}

@Composable
fun VerificationCodeScreenContent(
    phoneOrEmail: String = "+998901234567",
    uiState: VerificationCodeContract.UiState = VerificationCodeContract.UiState(),
    onEvent: (VerificationCodeContract.Intent) -> Unit = {}
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
            IconButton(onClick = { onEvent(VerificationCodeContract.Intent.OnBackClick) }) {
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
                text = "Enter confirmation code",
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
                text = "Enter the code we sent to $phoneOrEmail",
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color.Gray,
                    fontFamily = FontFamily(listOf(Font(R.font.inter_regular)))
                )
            )

            Spacer(modifier = Modifier.height(MaterialTheme.dimens.spacingExtraLarge))

            // Code input boxes
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                repeat(6) { index ->
                    CodeInputBox(
                        value = uiState.code.getOrNull(index)?.toString() ?: "",
                        isFocused = index == uiState.code.length
                    )
                }
            }

            Spacer(modifier = Modifier.height(MaterialTheme.dimens.spacingMedium))


            val focusRequester = remember { FocusRequester() }

            LaunchedEffect(Unit) {
                focusRequester.requestFocus()
            }

            BasicTextField(
                value = uiState.code,
                onValueChange = {
                    if (it.length <= 6 && it.all { char -> char.isDigit() }) {
                        onEvent(VerificationCodeContract.Intent.OnCodeChange(it))
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .focusRequester(focusRequester)
                    .alpha(0f)
            )

            Spacer(modifier = Modifier.height(MaterialTheme.dimens.spacingLarge))


            TextButton(
                onClick = { onEvent(VerificationCodeContract.Intent.OnResendCodeClick) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "I didn't receive a code",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MainColor,
                        fontFamily = FontFamily(listOf(Font(R.font.inter_semibold)))
                    )
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // Test hint
            Text(
                text = "Test code: 123456",
                style = TextStyle(
                    fontSize = 12.sp,
                    color = Color.Gray,
                    fontFamily = FontFamily(listOf(Font(R.font.inter_regular)))
                ),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(MaterialTheme.dimens.spacingMedium))

            // Next button
            Button(
                onClick = { onEvent(VerificationCodeContract.Intent.OnNextClick) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(MaterialTheme.dimens.buttonHeightLarge),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MainColor
                ),
                shape = RoundedCornerShape(MaterialTheme.dimens.cornerRadiusMedium),
                enabled = uiState.code.length == 6
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

@Composable
fun CodeInputBox(
    value: String,
    isFocused: Boolean
) {
    Box(
        modifier = Modifier
            .size(48.dp)
            .border(
                width = 2.dp,
                color = if (isFocused) MainColor else Color(0xFFE0E0E0),
                shape = RoundedCornerShape(8.dp)
            )
            .background(Color.White, RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = value,
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun VerificationCodeScreenPreview() {
    VerificationCodeScreenContent()
}

