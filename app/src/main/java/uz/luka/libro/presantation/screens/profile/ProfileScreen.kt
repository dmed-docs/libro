package uz.luka.libro.presantation.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import uz.luka.libro.presantation.components.home.RecomendationCardUi
import uz.luka.libro.presantation.components.profile.ProfileAboutSavedUi
import uz.luka.libro.presantation.components.profile.ProfileMainUi
import uz.luka.libro.presantation.components.profile.ProfileTopContent
import uz.luka.libro.presantation.screens.editprofile.EditProfileScreen
import uz.luka.libro.presantation.screens.search.SearchUsersScreen
import uz.luka.libro.presantation.screens.settings.SettingsScreen
import uz.luka.libro.presantation.screens.shareprofile.ShareProfileScreen
import uz.luka.libro.ui.theme.MainColor
import uz.luka.libro.ui.theme.dimens

class ProfileScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel: ProfileViewModel = getViewModel()
        val uiState by viewModel.uiState.collectAsState()
        val navigator = LocalNavigator.currentOrThrow

        // Screen ga qaytganda profil ma'lumotlarini yangilash
        LaunchedEffect(Unit) {
            viewModel.onEventDispatcher(ProfileContract.Intent.LoadProfile)
        }

        // Side effects
        LaunchedEffect(Unit) {
            // Har safar screen ochilganda profilni yangilash
            viewModel.onEventDispatcher(ProfileContract.Intent.LoadProfile)
            
            viewModel.sideEffect.collect { effect ->
                when (effect) {
                    ProfileContract.SideEffect.NavigateToEditProfile -> {
                        navigator.push(EditProfileScreen())
                    }
                    is ProfileContract.SideEffect.NavigateToShareProfile -> {
                        navigator.push(
                            ShareProfileScreen(
                                username = effect.username,
                                fullName = effect.fullName
                            )
                        )
                    }
                    ProfileContract.SideEffect.NavigateToSettings -> {
                        navigator.push(SettingsScreen())
                    }
                    ProfileContract.SideEffect.NavigateToSearchUsers -> {
                        navigator.push(SearchUsersScreen())
                    }
                    ProfileContract.SideEffect.NavigateToLogin -> {
                        // TODO: Navigate to login screen
                        println("🔵 LIBRO: Navigate to login")
                    }
                }
            }
        }

        ProfileScreenContent(
            isLoading = uiState.isLoading,
            username = uiState.userProfile?.username ?: "",
            fullName = uiState.userProfile?.username ?: "",
            bio = uiState.userProfile?.bio ?: "",
            readsCount = uiState.userProfile?.readsCount ?: 0,
            followingCount = uiState.userProfile?.followingCount ?: 0,
            followersCount = uiState.userProfile?.followersCount ?: 0,
            avatarUrl = uiState.userProfile?.avatarUrl,
            errorMessage = uiState.errorMessage,
            onEditProfileClick = { viewModel.onEventDispatcher(ProfileContract.Intent.OnEditProfileClick) },
            onShareProfileClick = { viewModel.onEventDispatcher(ProfileContract.Intent.OnShareProfileClick) },
            onMenuClick = { viewModel.onEventDispatcher(ProfileContract.Intent.OnSettingsClick) },
            onAddPeopleClick = { viewModel.onEventDispatcher(ProfileContract.Intent.OnAddPeopleClick) }
        )
    }
}

@Composable
fun ProfileScreenContent(
    isLoading: Boolean = false,
    username: String = "xojiakbar_2329",
    fullName: String = "Xojiakbar",
    bio: String = "",
    readsCount: Int = 0,
    followingCount: Int = 0,
    followersCount: Int = 0,
    avatarUrl: String? = null,
    errorMessage: String? = null,
    onEditProfileClick: () -> Unit = {},
    onShareProfileClick: () -> Unit = {},
    onMenuClick: () -> Unit = {},
    onAddPeopleClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = MainColor
            )
        } else if (errorMessage != null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(MaterialTheme.dimens.paddingMedium),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Xatolik: $errorMessage",
                    color = Color.Red
                )
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(MaterialTheme.dimens.paddingMedium)
            ) {
                ProfileTopContent(
                    username = username,
                    onMenuClick = onMenuClick
                )

                Spacer(modifier = Modifier.size(MaterialTheme.dimens.spacingSmall))

                ProfileMainUi(
                    profileImageRes = null,
                    avatarUrl = avatarUrl,
                    fullName = fullName,
                    bio = bio,
                    readsCount = readsCount,
                    followingCount = followingCount,
                    followersCount = followersCount,
                    onEditProfileClick = onEditProfileClick,
                    onShareProfileClick = onShareProfileClick,
                    onAddPeopleClick = onAddPeopleClick
                )

                Spacer(modifier = Modifier.size(MaterialTheme.dimens.spacingTiny))

                ProfileAboutSavedUi()

                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .shadow(1.dp)
                )

                Spacer(modifier = Modifier.size(MaterialTheme.dimens.spacingSmall))

                LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                    verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimens.spacingMedium),
                    horizontalArrangement = Arrangement.spacedBy(MaterialTheme.dimens.spacingMedium)
                ) {
                    items(count = 0) { // TODO: Display actual saved books
                        RecomendationCardUi()
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreenContent()
}