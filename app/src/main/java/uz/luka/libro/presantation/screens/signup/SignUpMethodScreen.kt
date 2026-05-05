package uz.luka.libro.presantation.screens.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import uz.luka.libro.ui.theme.MainColor
import uz.luka.libro.ui.theme.dimens

class SignUpMethodScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = getViewModel<SignUpMethodViewModel>()
        SignUpMethodScreenContent(
            onEvent = viewModel::onEventDispatcher
        )
    }
}

@Composable
fun SignUpMethodScreenContent(
    onEvent: (SignUpMethodContract.Intent) -> Unit = {}
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
            // Top Bar
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { onEvent(SignUpMethodContract.Intent.OnBackClick) }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_user),
                        contentDescription = "Back",
                        tint = MainColor,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(MaterialTheme.dimens.spacingLarge))

            // Title
            Text(
                text = "Create your account",
                style = TextStyle(
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontFamily = FontFamily(listOf(Font(R.font.roboto_bold)))
                ),
                modifier = Modifier.padding(horizontal = MaterialTheme.dimens.paddingSmall)
            )

            Spacer(modifier = Modifier.height(MaterialTheme.dimens.spacingMedium))

            // Description
            Text(
                text = "Join Libro community and discover thousands of books",
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color.Gray,
                    fontFamily = FontFamily(listOf(Font(R.font.inter_regular)))
                ),
                modifier = Modifier.padding(horizontal = MaterialTheme.dimens.paddingSmall)
            )

            Spacer(modifier = Modifier.height(MaterialTheme.dimens.spacingExtraLarge * 2))

            // Sign up with Phone Button
            Button(
                onClick = { onEvent(SignUpMethodContract.Intent.OnPhoneMethodClick) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(MaterialTheme.dimens.buttonHeightLarge),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MainColor
                ),
                shape = RoundedCornerShape(MaterialTheme.dimens.cornerRadiusMedium)
            ) {
                Text(
                    text = "Sign up with phone number",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontFamily = FontFamily(listOf(Font(R.font.worksans_semibold)))
                    )
                )
            }

            Spacer(modifier = Modifier.height(MaterialTheme.dimens.spacingMedium))

            // Sign up with Email Button
            OutlinedButton(
                onClick = { onEvent(SignUpMethodContract.Intent.OnEmailMethodClick) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(MaterialTheme.dimens.buttonHeightLarge),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = MainColor
                ),
                shape = RoundedCornerShape(MaterialTheme.dimens.cornerRadiusMedium),
                border = androidx.compose.foundation.BorderStroke(2.dp, MainColor)
            ) {
                Text(
                    text = "Sign up with email",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily(listOf(Font(R.font.worksans_semibold)))
                    )
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // Already have account
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Already have an account? ",
                    style = TextStyle(
                        fontSize = 14.sp,
                        color = Color.Gray,
                        fontFamily = FontFamily(listOf(Font(R.font.inter_regular)))
                    )
                )
                TextButton(onClick = { onEvent(SignUpMethodContract.Intent.OnBackClick) }) {
                    Text(
                        text = "Log in",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = MainColor,
                            fontFamily = FontFamily(listOf(Font(R.font.inter_bold)))
                        )
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpMethodScreenPreview() {
    SignUpMethodScreenContent()
}
