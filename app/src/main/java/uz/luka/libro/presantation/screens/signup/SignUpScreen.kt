package uz.luka.libro.presantation.screens.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import uz.luka.libro.R
import uz.luka.libro.presantation.components.app.TextFieldUi
import uz.luka.libro.presantation.components.sign.ButtonTextsUi
import uz.luka.libro.presantation.components.sign.CustomBtnOrBtn
import uz.luka.libro.presantation.components.sign.RegisterTopUi
import uz.luka.libro.ui.theme.DarkGray
import uz.luka.libro.ui.theme.dimens

class SignUpScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = getViewModel<SignUpViewModel>()
        SignUpScreenContent(
            viewModel.uiState.collectAsState().value ,
            viewModel::onEventDispatcher
        )
    }
}

@Composable
fun SignUpScreenContent(
    uiState : SignUpContract.UiState = SignUpContract.UiState(),
    onEventDispatchers : (SignUpContract.Intent) -> Unit = {}
) {

    Box (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(MaterialTheme.dimens.small1)
    ){

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            RegisterTopUi(
                topText = "Sign Up" ,
                onClickBackBtn = {
                    onEventDispatchers(SignUpContract.Intent.OnClickBackBtn)
                }
            )

            TextFieldUi(
                value = uiState.userName ,
                onChangeValue = { value ->
                    onEventDispatchers(SignUpContract.Intent.OnChangeUserName(value))
                } ,
                label = "User name" ,
                leadingIcon = R.drawable.ic_user ,
                trailingIcon = if(uiState.userTrailingIconState) R.drawable.ic_cross else 0 ,
                cross = 1,
                onClickTrailingIc = {
                    onEventDispatchers(SignUpContract.Intent.OnClickUserCrossIc)
                }
            )

            Spacer(modifier = Modifier.size(MaterialTheme.dimens.medium1))

            TextFieldUi(
                value = uiState.gmail ,
                onChangeValue = { value ->
                    onEventDispatchers(SignUpContract.Intent.OnChangeGmail(value))
                } ,
                label = "Gmail" ,
                leadingIcon = R.drawable.ic_gmail ,
                trailingIcon = if (uiState.gmailTrailingIconState) R.drawable.ic_cross else 0 ,
                cross = 1,
                onClickTrailingIc = {
                    onEventDispatchers(SignUpContract.Intent.OnClickGmailCrossIc)
                }
            )

            Spacer(modifier = Modifier.size(MaterialTheme.dimens.medium1))

            TextFieldUi(
                label = "Password",
                value = uiState.password ,
                onChangeValue = { value ->
                    onEventDispatchers(SignUpContract.Intent.OnChangePassword(value))
                } ,
                trailingIcon = if (uiState.passwordIcState) R.drawable.ic_view else R.drawable.ic_hide ,
                passwordState = uiState.passwordIcState ,
                onClickTrailingIc = {
                    onEventDispatchers(SignUpContract.Intent.OnClickPasswordTIc)
                } ,
                leadingIcon = R.drawable.ic_password
            )

            Spacer(modifier = Modifier.size(MaterialTheme.dimens.medium1))

            TextFieldUi(
                label = "Re-Password",
                value = uiState.rePassword ,
                onChangeValue = { value ->
                    onEventDispatchers(SignUpContract.Intent.OnChangePassword(value))
                } ,
                trailingIcon = if (uiState.rePasswordIcState) R.drawable.ic_view else R.drawable.ic_hide ,
                passwordState = uiState.rePasswordIcState ,
                onClickTrailingIc = {
                    onEventDispatchers(SignUpContract.Intent.OnClickRePasswordTIc)
                } ,
                leadingIcon = R.drawable.ic_repassword
            )

            Spacer(modifier = Modifier.size(MaterialTheme.dimens.spacingMedium))

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Forget password" ,
                style = TextStyle(
                    color = DarkGray ,
                    fontFamily = FontFamily(listOf(Font(R.font.inter_regular))) ,
                    fontSize = 14.sp
                ) ,
                textAlign = TextAlign.End
            )
            CustomBtnOrBtn(
                firstBtnName = "Sign Up",
                clickFirstBtn = {
                    onEventDispatchers(SignUpContract.Intent.OnClickSignUpBtn)
                }
            )

        }

        ButtonTextsUi(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = MaterialTheme.dimens.paddingLarge) ,
            firstText = "Have an account?",
            secondText = "Sign In." ,
            onClickSecondBtn = {
                onEventDispatchers(SignUpContract.Intent.OnClickSignUpBtn)
            }
        )

    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    SignUpScreenContent()
}