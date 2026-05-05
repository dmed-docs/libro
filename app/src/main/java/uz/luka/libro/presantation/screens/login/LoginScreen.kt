package uz.luka.libro.presantation.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import uz.luka.libro.R
import uz.luka.libro.presantation.components.app.TextFieldUi
import uz.luka.libro.ui.theme.DarkGray
import uz.luka.libro.ui.theme.MainColor
import uz.luka.libro.ui.theme.dimens

class LoginScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = getViewModel<LoginViewModel>()
        LoginScreenContent(
            uiState = viewModel.uiState.collectAsState().value,
            onEvent = viewModel::onEventDispatcher
        )
    }
}

@Composable
fun LoginScreenContent(
    uiState: LoginContract.UiState = LoginContract.UiState(),
    onEvent: (LoginContract.Intent) -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(MaterialTheme.dimens.paddingLarge),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Top Section with Logo
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Logo
                Image(
                    painter = painterResource(id = R.drawable.splash_logo_second),
                    contentDescription = "Libro Logo",
                    modifier = Modifier.size(120.dp) ,
                    colorFilter = ColorFilter.tint(MainColor)
                )

                Spacer(modifier = Modifier.height(MaterialTheme.dimens.spacingExtraLarge * 2))

                // Username/Email Field
                TextFieldUi(
                    value = uiState.usernameOrEmail,
                    onChangeValue = { onEvent(LoginContract.Intent.OnUsernameOrEmailChange(it)) },
                    label = "Username, email or phone",
                    borderColor = Color(0xFFE0E0E0),
                    isModifier = true,
                    modifier = Modifier.fillMaxWidth() ,
                    leadingIcon = R.drawable.ic_profile
                )

                Spacer(modifier = Modifier.height(MaterialTheme.dimens.spacingMedium))

                // Password Field
                TextFieldUi(
                    value = uiState.password,
                    onChangeValue = { onEvent(LoginContract.Intent.OnPasswordChange(it)) },
                    label = "Password",
                    trailingIcon = if (uiState.passwordVisible) R.drawable.ic_view else R.drawable.ic_hide,
                    passwordState = uiState.passwordVisible,
                    onClickTrailingIc = { onEvent(LoginContract.Intent.OnPasswordVisibilityToggle) },
                    borderColor = Color(0xFFE0E0E0),
                    isModifier = true,
                    modifier = Modifier.fillMaxWidth() ,
                    leadingIcon = R.drawable.ic_password
                )

                Spacer(modifier = Modifier.height(MaterialTheme.dimens.spacingMedium))

                // Login Button
                Button(
                    onClick = { onEvent(LoginContract.Intent.OnLoginClick) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(MaterialTheme.dimens.buttonHeightLarge),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MainColor
                    ),
                    shape = RoundedCornerShape(MaterialTheme.dimens.cornerRadiusMedium),
                    enabled = !uiState.isLoading
                ) {
                    if (uiState.isLoading) {
                        CircularProgressIndicator(
                            color = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    } else {
                        Text(
                            text = "Log in",
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                fontFamily = FontFamily(listOf(Font(R.font.worksans_semibold)))
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(MaterialTheme.dimens.spacingLarge))

                // Forgot Password
                Text(
                    text = "Forgot password?",
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = MainColor,
                        fontWeight = FontWeight.Medium,
                        fontFamily = FontFamily(listOf(Font(R.font.inter_semibold)))
                    ),
                    modifier = Modifier.clickable {
                        onEvent(LoginContract.Intent.OnForgotPasswordClick)
                    }
                )
            }

            // Bottom Section
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(bottom = MaterialTheme.dimens.paddingLarge)
            ) {
                // Divider with OR
                Text(
                    text = "OR",
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = Color.Gray,
                        fontWeight = FontWeight.Medium
                    ),
                    modifier = Modifier.padding(vertical = MaterialTheme.dimens.spacingMedium)
                )

                // Create Account Button
                OutlinedButton(
                    onClick = { onEvent(LoginContract.Intent.OnCreateAccountClick) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(MaterialTheme.dimens.buttonHeightLarge),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = Color.Transparent,
                        contentColor = MainColor
                    ),
                    shape = RoundedCornerShape(MaterialTheme.dimens.cornerRadiusMedium),
                    border = androidx.compose.foundation.BorderStroke(
                        2.dp,
                        MainColor
                    )
                ) {
                    Text(
                        text = "Create new account",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily(listOf(Font(R.font.worksans_semibold)))
                        )
                    )
                }

                Spacer(modifier = Modifier.height(MaterialTheme.dimens.spacingLarge))

                // Libro branding
                Text(
                    text = "Libro",
                    style = TextStyle(
                        fontSize = 18.sp,
                        color = MainColor,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily(listOf(Font(R.font.roboto_bold)))
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreenContent()
}
