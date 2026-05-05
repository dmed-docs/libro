package uz.luka.libro.presantation.screens.signup.email

import androidx.compose.foundation.background
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
import uz.luka.libro.presantation.components.app.TextFieldUi
import uz.luka.libro.ui.theme.MainColor
import uz.luka.libro.ui.theme.dimens

class SignUpWithEmailScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = getViewModel<SignUpWithEmailViewModel>()
        SignUpWithEmailScreenContent(
            uiState = viewModel.uiState.collectAsState().value,
            onEvent = viewModel::onEventDispatcher
        )
    }
}

@Composable
fun SignUpWithEmailScreenContent(
    uiState: SignUpWithEmailContract.UiState = SignUpWithEmailContract.UiState(),
    onEvent: (SignUpWithEmailContract.Intent) -> Unit = {}
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
            IconButton(onClick = { onEvent(SignUpWithEmailContract.Intent.OnBackClick) }) {
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
                text = "Enter your email",
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
                text = "Enter the email where you can be contacted. No one will see this on your profile.",
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color.Gray,
                    fontFamily = FontFamily(listOf(Font(R.font.inter_regular)))
                )
            )

            Spacer(modifier = Modifier.height(MaterialTheme.dimens.spacingExtraLarge))

            // Email field
            TextFieldUi(
                value = uiState.email,
                onChangeValue = { onEvent(SignUpWithEmailContract.Intent.OnEmailChange(it)) },
                label = "Email",
                borderColor = Color(0xFFE0E0E0),
                isModifier = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.weight(1f))

            // Next button
            Button(
                onClick = { onEvent(SignUpWithEmailContract.Intent.OnNextClick) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(MaterialTheme.dimens.buttonHeightLarge),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MainColor
                ),
                shape = RoundedCornerShape(MaterialTheme.dimens.cornerRadiusMedium),
                enabled = uiState.email.isNotEmpty()
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

            Spacer(modifier = Modifier.height(MaterialTheme.dimens.spacingMedium))

            // Sign up with phone
            TextButton(
                onClick = { onEvent(SignUpWithEmailContract.Intent.OnSignUpWithPhoneClick) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Sign up with phone number",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MainColor,
                        fontFamily = FontFamily(listOf(Font(R.font.inter_semibold)))
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpWithEmailScreenPreview() {
    SignUpWithEmailScreenContent()
}

