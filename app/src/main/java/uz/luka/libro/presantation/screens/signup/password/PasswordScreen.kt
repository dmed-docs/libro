package uz.luka.libro.presantation.screens.signup.password

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import uz.luka.libro.ui.theme.MainColor
import uz.luka.libro.ui.theme.dimens

class PasswordScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel: PasswordViewModel = getViewModel()
        val uiState = viewModel.uiState.collectAsState().value
        
        PasswordScreenContent(
            password = uiState.password,
            confirmPassword = uiState.confirmPassword,
            isPasswordVisible = uiState.isPasswordVisible,
            isConfirmPasswordVisible = uiState.isConfirmPasswordVisible,
            errorMessage = uiState.errorMessage,
            isLoading = uiState.isLoading,
            onPasswordChange = { viewModel.onEventDispatcher(PasswordContract.Intent.OnPasswordChange(it)) },
            onConfirmPasswordChange = { viewModel.onEventDispatcher(PasswordContract.Intent.OnConfirmPasswordChange(it)) },
            onTogglePasswordVisibility = { viewModel.onEventDispatcher(PasswordContract.Intent.TogglePasswordVisibility) },
            onToggleConfirmPasswordVisibility = { viewModel.onEventDispatcher(PasswordContract.Intent.ToggleConfirmPasswordVisibility) },
            onNextClick = { viewModel.onEventDispatcher(PasswordContract.Intent.OnNextClick) },
            onBackClick = { viewModel.onEventDispatcher(PasswordContract.Intent.OnBackClick) }
        )
    }
}

@Composable
fun PasswordScreenContent(
    password: String,
    confirmPassword: String,
    isPasswordVisible: Boolean,
    isConfirmPasswordVisible: Boolean,
    errorMessage: String?,
    isLoading: Boolean,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    onTogglePasswordVisibility: () -> Unit,
    onToggleConfirmPasswordVisibility: () -> Unit,
    onNextClick: () -> Unit,
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(MaterialTheme.dimens.paddingLarge)
    ) {
        // Back button
        IconButton(
            onClick = onBackClick,
            enabled = !isLoading,
            modifier = Modifier.size(MaterialTheme.dimens.iconSizeLarge)
        ) {
            Text(
                text = "←",
                style = MaterialTheme.typography.headlineMedium,
                color = if (isLoading) Color.Gray else Color.Black
            )
        }
        
        Spacer(modifier = Modifier.height(MaterialTheme.dimens.spacingLarge))
        
        // Title
        Text(
            text = "Parol yarating",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontWeight = FontWeight.Bold
            ),
            color = Color.Black
        )
        
        Spacer(modifier = Modifier.height(MaterialTheme.dimens.spacingSmall))
        
        // Subtitle
        Text(
            text = "Kamida 6 ta belgidan iborat parol kiriting",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
        
        Spacer(modifier = Modifier.height(MaterialTheme.dimens.spacingLarge))
        
        // Password field
        OutlinedTextField(
            value = password,
            onValueChange = onPasswordChange,
            label = { Text("Parol") },
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = onTogglePasswordVisibility) {
                    Text(
                        text = if (isPasswordVisible) "👁" else "👁‍🗨",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MainColor,
                focusedLabelColor = MainColor,
                cursorColor = MainColor
            )
        )
        
        Spacer(modifier = Modifier.height(MaterialTheme.dimens.spacingMedium))
        
        // Confirm password field
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = onConfirmPasswordChange,
            label = { Text("Parolni tasdiqlang") },
            visualTransformation = if (isConfirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = onToggleConfirmPasswordVisibility) {
                    Text(
                        text = if (isConfirmPasswordVisible) "👁" else "👁‍🗨",
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MainColor,
                focusedLabelColor = MainColor,
                cursorColor = MainColor
            )
        )
        
        // Error message
        if (errorMessage != null) {
            Spacer(modifier = Modifier.height(MaterialTheme.dimens.spacingSmall))
            Text(
                text = errorMessage,
                style = MaterialTheme.typography.bodySmall,
                color = MainColor
            )
        }
        
        Spacer(modifier = Modifier.weight(1f))
        
        // Next button
        Button(
            onClick = onNextClick,
            enabled = !isLoading && password.isNotEmpty() && confirmPassword.isNotEmpty(),
            modifier = Modifier
                .fillMaxWidth()
                .height(MaterialTheme.dimens.buttonHeightLarge),
            colors = ButtonDefaults.buttonColors(
                containerColor = MainColor,
                disabledContainerColor = MainColor.copy(alpha = 0.3f)
            ),
            shape = RoundedCornerShape(MaterialTheme.dimens.cornerRadiusSmall)
        ) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.size(MaterialTheme.dimens.iconSizeMedium),
                    color = Color.White
                )
            } else {
                Text(
                    text = "Keyingisi",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.SemiBold
                    ),
                    color = Color.White
                )
            }
        }
        
        Spacer(modifier = Modifier.height(MaterialTheme.dimens.spacingSmall))
    }
}
