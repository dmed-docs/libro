package uz.luka.libro.presantation.screens.editprofile

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil3.compose.AsyncImage
import uz.luka.libro.R
import uz.luka.libro.presantation.components.app.ButtonUi
import uz.luka.libro.presantation.components.app.TextFieldUi
import uz.luka.libro.presantation.components.app.TextUi
import uz.luka.libro.ui.theme.Black
import uz.luka.libro.ui.theme.DarkGray
import uz.luka.libro.ui.theme.MainColor
import uz.luka.libro.ui.theme.dimens
import java.io.File

class EditProfileScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel: EditProfileViewModel = getViewModel()
        val uiState by viewModel.uiState.collectAsState()
        val navigator = LocalNavigator.currentOrThrow
        val context = LocalContext.current

        // Side effects
        LaunchedEffect(Unit) {
            viewModel.sideEffect.collect { effect ->
                when (effect) {
                    EditProfileContract.SideEffect.NavigateBack -> {
                        navigator.pop()
                    }
                    is EditProfileContract.SideEffect.ShowToast -> {
                        Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        EditProfileScreenContent(
            isLoading = uiState.isLoading,
            isSaving = uiState.isSaving,
            isUploadingImage = uiState.isUploadingImage,
            username = uiState.username,
            location = uiState.location,
            bio = uiState.bio,
            gender = uiState.gender,
            websiteUrl = uiState.websiteUrl,
            profileImageUri = uiState.profileImageUri,
            avatarUrl = uiState.avatarUrl,
            onUsernameChange = { viewModel.onEventDispatcher(EditProfileContract.Intent.OnUsernameChange(it)) },
            onLocationChange = { viewModel.onEventDispatcher(EditProfileContract.Intent.OnLocationChange(it)) },
            onBioChange = { viewModel.onEventDispatcher(EditProfileContract.Intent.OnBioChange(it)) },
            onGenderChange = { viewModel.onEventDispatcher(EditProfileContract.Intent.OnGenderChange(it)) },
            onWebsiteUrlChange = { viewModel.onEventDispatcher(EditProfileContract.Intent.OnWebsiteUrlChange(it)) },
            onProfileImageSelected = { viewModel.onEventDispatcher(EditProfileContract.Intent.OnProfileImageSelected(it)) },
            onSaveClick = { viewModel.onEventDispatcher(EditProfileContract.Intent.OnSaveClick) },
            onBackClick = { viewModel.onEventDispatcher(EditProfileContract.Intent.OnBackClick) }
        )
    }
}

@Composable
fun EditProfileScreenContent(
    isLoading: Boolean = false,
    isSaving: Boolean = false,
    isUploadingImage: Boolean = false,
    username: String = "",
    location: String = "",
    bio: String = "",
    gender: String = "Prefer not to say",
    websiteUrl: String = "",
    profileImageUri: Uri? = null,
    avatarUrl: String? = null,
    onUsernameChange: (String) -> Unit = {},
    onLocationChange: (String) -> Unit = {},
    onBioChange: (String) -> Unit = {},
    onGenderChange: (String) -> Unit = {},
    onWebsiteUrlChange: (String) -> Unit = {},
    onProfileImageSelected: (Uri) -> Unit = {},
    onSaveClick: () -> Unit = {},
    onBackClick: () -> Unit = {}
) {
    val navigator = LocalNavigator.currentOrThrow
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    
    var localUsername by remember(username) { mutableStateOf(username) }
    var localLocation by remember(location) { mutableStateOf(location) }
    var localBio by remember(bio) { mutableStateOf(bio) }
    var selectedGender by remember(gender) { mutableStateOf(gender) }
    var showGenderDropdown by remember { mutableStateOf(false) }
    var localProfileImageUri by remember(profileImageUri) { mutableStateOf(profileImageUri) }
    
    val genderOptions = listOf("Male", "Female", "Other", "Prefer not to say")
    
    // Gallery Launcher
    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            localProfileImageUri = it
            onProfileImageSelected(it)
        }
    }
    
    // Camera Launcher
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success ->
        if (success && localProfileImageUri != null) {
            onProfileImageSelected(localProfileImageUri!!)
        }
    }
    
    // Camera Permission Launcher
    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            // Create temp file for camera
            val photoFile = File(context.cacheDir, "profile_${System.currentTimeMillis()}.jpg")
            val uri = FileProvider.getUriForFile(
                context,
                "${context.packageName}.fileprovider",
                photoFile
            )
            localProfileImageUri = uri
            cameraLauncher.launch(uri)
        } else {
            Toast.makeText(context, "Camera permission denied", Toast.LENGTH_SHORT).show()
        }
    }
    
    fun openCamera() {
        when (PackageManager.PERMISSION_GRANTED) {
            ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) -> {
                // Permission already granted
                val photoFile = File(context.cacheDir, "profile_${System.currentTimeMillis()}.jpg")
                val uri = FileProvider.getUriForFile(
                    context,
                    "${context.packageName}.fileprovider",
                    photoFile
                )
                localProfileImageUri = uri
                cameraLauncher.launch(uri)
            }
            else -> {
                // Request permission
                cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
            }
        }
    }
    
    fun openGallery() {
        galleryLauncher.launch("image/*")
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Top Bar
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(MaterialTheme.dimens.topBarHeight)
                .padding(horizontal = MaterialTheme.dimens.paddingMedium),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "Back",
                tint = Black,
                modifier = Modifier
                    .size(24.dp)
                    .clickable { onBackClick() }
            )
            
            Spacer(modifier = Modifier.weight(1f))
            
            TextUi(
                text = "Edit Profile",
                color = Black,
                fontSize = 18,
                fontFamily = R.font.inter_bold
            )
            
            Spacer(modifier = Modifier.weight(1f))
            
            // Save button
            if (isSaving) {
                CircularProgressIndicator(
                    modifier = Modifier.size(24.dp),
                    color = MainColor,
                    strokeWidth = 2.dp
                )
            } else {
                TextUi(
                    text = "Save",
                    color = MainColor,
                    fontSize = 16,
                    fontFamily = R.font.inter_semibold,
                    modifier = Modifier.clickable { onSaveClick() }
                )
            }
        }
        
        // Scrollable Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = MaterialTheme.dimens.paddingLarge),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(MaterialTheme.dimens.spacingLarge))
            
            // Profile Photo Section
            Row(
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimens.spacingLarge),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Camera Icon - Change Photo
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(MainColor.copy(alpha = 0.1f))
                        .border(2.dp, MainColor, CircleShape)
                        .clickable { openCamera() },
                    contentAlignment = Alignment.Center
                ) {
                    if (localProfileImageUri != null || avatarUrl != null) {
                        AsyncImage(
                            model = localProfileImageUri ?: avatarUrl,
                            contentDescription = "Profile Photo",
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(CircleShape)
                        )
                        if (isUploadingImage) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.Black.copy(alpha = 0.5f)),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(24.dp),
                                    color = Color.White,
                                    strokeWidth = 2.dp
                                )
                            }
                        }
                    } else {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_camera),
                            contentDescription = "Take Photo",
                            tint = MainColor,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }
                
                // Gallery Icon - Choose from Gallery
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(MainColor.copy(alpha = 0.1f))
                        .border(2.dp, MainColor, CircleShape)
                        .clickable { openGallery() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_gallery),
                        contentDescription = "Choose from Gallery",
                        tint = MainColor,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(MaterialTheme.dimens.spacingSmall))
            
            TextUi(
                text = "Change photo or avatar",
                color = DarkGray,
                fontSize = 14,
                fontFamily = R.font.inter_regular
            )
            
            Spacer(modifier = Modifier.height(MaterialTheme.dimens.spacingExtraLarge))
            
            // Username Field
            TextFieldUi(
                isModifier = true,
                modifier = Modifier
                    .fillMaxWidth(),
                value = localUsername,
                onChangeValue = { 
                    localUsername = it
                    onUsernameChange(it)
                },
                label = "Username",
                cross = 1
            )
            
            Spacer(modifier = Modifier.height(MaterialTheme.dimens.spacingSmall))
            
            // Location Field
            TextFieldUi(
                isModifier = true,
                modifier = Modifier
                    .fillMaxWidth(),
                value = localLocation,
                onChangeValue = { 
                    localLocation = it
                    onLocationChange(it)
                },
                label = "Location",
                cross = 1
            )
            
            Spacer(modifier = Modifier.height(MaterialTheme.dimens.spacingSmall))
            
            // Bio Field
            TextFieldUi(
                isModifier = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                value = localBio,
                onChangeValue = { 
                    localBio = it
                    onBioChange(it)
                },
                label = "Bio",
                cross = 1,
                singleLine = false,
                maxLines = 4
            )
            
            Spacer(modifier = Modifier.height(MaterialTheme.dimens.spacingExtraLarge))
            
            // Add Link Section
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { /* TODO: Add link */ }
                    .padding(vertical = MaterialTheme.dimens.paddingSmall),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextUi(
                    text = "Add link",
                    color = MainColor,
                    fontSize = 16,
                    fontFamily = R.font.inter_medium
                )
            }
            
            Spacer(modifier = Modifier.height(MaterialTheme.dimens.spacingLarge))
            
            // Add Banners Section
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                TextUi(
                    text = "Add banners",
                    color = Black,
                    fontSize = 16,
                    fontFamily = R.font.inter_semibold
                )
                Spacer(modifier = Modifier.height(MaterialTheme.dimens.spacingTiny))
                TextUi(
                    text = "Add music, profiles and more.",
                    color = DarkGray,
                    fontSize = 14,
                    fontFamily = R.font.inter_regular
                )
            }
            
            Spacer(modifier = Modifier.height(MaterialTheme.dimens.spacingExtraLarge))
            
            // Gender Dropdown
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Gender Selector
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(MaterialTheme.dimens.cornerRadiusMedium))
                            .background(Color(0xFFF5F5F5))
                            .clickable { showGenderDropdown = !showGenderDropdown }
                            .padding(MaterialTheme.dimens.paddingMedium)
                    ) {
                        TextUi(
                            text = "Gender",
                            color = DarkGray,
                            fontSize = 12,
                            fontFamily = R.font.inter_regular
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            TextUi(
                                text = selectedGender,
                                color = Black,
                                fontSize = 16,
                                fontFamily = R.font.inter_medium
                            )
                            Icon(
                                painter = painterResource(id = R.drawable.ic_arrow_down),
                                contentDescription = "Dropdown",
                                tint = DarkGray,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                    
                    // Dropdown Menu
                    AnimatedVisibility(
                        visible = showGenderDropdown,
                        enter = fadeIn() + expandVertically(),
                        exit = fadeOut() + shrinkVertically()
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 4.dp)
                                .clip(RoundedCornerShape(MaterialTheme.dimens.cornerRadiusMedium))
                                .background(Color.White)
                                .border(
                                    1.dp,
                                    Color(0xFFE0E0E0),
                                    RoundedCornerShape(MaterialTheme.dimens.cornerRadiusMedium)
                                )
                        ) {
                            genderOptions.forEach { gender ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            selectedGender = gender
                                            showGenderDropdown = false
                                            onGenderChange(gender)
                                        }
                                        .padding(MaterialTheme.dimens.paddingMedium),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    TextUi(
                                        text = gender,
                                        color = if (gender == selectedGender) MainColor else Black,
                                        fontSize = 16,
                                        fontFamily = if (gender == selectedGender) R.font.inter_semibold else R.font.inter_regular
                                    )
                                }
                                if (gender != genderOptions.last()) {
                                    Spacer(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(1.dp)
                                            .background(Color(0xFFF0F0F0))
                                    )
                                }
                            }
                        }
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(MaterialTheme.dimens.spacingExtraLarge))
            
            // Switch to Professional Account
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { /* TODO: Switch to professional */ }
                    .padding(vertical = MaterialTheme.dimens.paddingSmall),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextUi(
                    text = "Switch to professional account",
                    color = MainColor,
                    fontSize = 16,
                    fontFamily = R.font.inter_medium
                )
            }
            
            Spacer(modifier = Modifier.height(MaterialTheme.dimens.spacingMedium))
            
            // Personal Information Settings
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { /* TODO: Personal info settings */ }
                    .padding(vertical = MaterialTheme.dimens.paddingSmall),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextUi(
                    text = "Personal information settings",
                    color = MainColor,
                    fontSize = 16,
                    fontFamily = R.font.inter_medium
                )
            }
            
            Spacer(modifier = Modifier.height(MaterialTheme.dimens.spacingExtraLarge))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EditProfileScreenPreview() {
    EditProfileScreenContent()
}
