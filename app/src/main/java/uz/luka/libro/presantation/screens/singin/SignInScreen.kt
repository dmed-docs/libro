package uz.luka.libro.presantation.screens.singin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.material3.MaterialTheme
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import uz.luka.libro.R
import uz.luka.libro.presantation.components.app.TextFieldUi
import uz.luka.libro.presantation.components.sign.ButtonTextsUi
import uz.luka.libro.presantation.components.sign.CustomBtnOrBtn
import uz.luka.libro.presantation.components.sign.RegisterTopUi
import uz.luka.libro.ui.theme.DarkGray
import uz.luka.libro.ui.theme.dimens

class SignInScreen : Screen {
    @Composable
    override fun Content() {

        val viewModel = getViewModel<SignInViewModel>()

        SignInScreenContent(
            viewModel.uiState.collectAsState().value ,
            viewModel::onEventDispatcher
        )
    }
}


@Composable
fun SignInScreenContent(
    uiState : SignInContract.UiState = SignInContract.UiState(),
    onEventDispatcher : (SignInContract.Intent) -> Unit = {}
) {

    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(MaterialTheme.dimens.paddingMedium)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize() ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            RegisterTopUi(
                topText = "SignIn" ,
                onClickBackBtn = {
                    onEventDispatcher(SignInContract.Intent.OnClickBackBtn)
                }
            )

            TextFieldUi(
                value = uiState.userName ,
                onChangeValue = { value ->
                    onEventDispatcher(SignInContract.Intent.OnChangeUserName(value))
                } ,
                label = "Username" ,
                leadingIcon = R.drawable.ic_user
            )

            Spacer(modifier = Modifier.size(MaterialTheme.dimens.spacingLarge))

            TextFieldUi(
                value = uiState.password ,
                onChangeValue = { value ->
                    onEventDispatcher(SignInContract.Intent.OnChangePassword(value))
                } ,
                trailingIcon = if (uiState.passwordIcState) R.drawable.ic_view else R.drawable.ic_hide ,
                passwordState = uiState.passwordIcState ,
                onClickTrailingIc = {
                    onEventDispatcher(SignInContract.Intent.OnClickPasswordTIc)
                } ,
                leadingIcon = R.drawable.ic_password ,
                label = "Password"
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
                firstBtnName = "Sign In",
                clickFirstBtn = {
                    onEventDispatcher(SignInContract.Intent.OnClickSignInBtn)
                }
            )

        }

        ButtonTextsUi(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = MaterialTheme.dimens.paddingLarge) ,
            firstText = "Don't have an account?",
            secondText = "Sign Up." ,
            onClickSecondBtn = {
                onEventDispatcher(SignInContract.Intent.OnClickSignUpText)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SignInScreenPreview() {
    SignInScreenContent()
}