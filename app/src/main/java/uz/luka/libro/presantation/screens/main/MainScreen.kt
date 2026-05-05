package uz.luka.libro.presantation.screens.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.MaterialTheme
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import com.rahad.riobottomnavigation.composables.RioBottomNavItemData
import com.rahad.riobottomnavigation.composables.RioBottomNavigation
import uz.luka.libro.R
import uz.luka.libro.presantation.screens.home.HomeScreen
import uz.luka.libro.presantation.screens.library.LibraryScreen
import uz.luka.libro.presantation.screens.notification.NotificationScreen
import uz.luka.libro.presantation.screens.profile.ProfileScreen
import uz.luka.libro.ui.theme.DarkGray
import uz.luka.libro.ui.theme.MainColor
import uz.luka.libro.ui.theme.dimens

class MainScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = getViewModel<MainViewModel>()
        MainScreenContent(
            viewModel.uiState.collectAsState().value
        )
    }
}

@Composable
fun MainScreenContent(
    uiState : MainContract.UiState = MainContract.UiState()
) {

    var selectedIndex = rememberSaveable { mutableIntStateOf(0) }

    val buttons = uiState.listIcons.mapIndexed { index, icon ->
        RioBottomNavItemData(
            imageVector = ImageVector.vectorResource(id = icon),
            selected = index == selectedIndex.intValue,
            onClick = { selectedIndex.intValue = index },
            label = uiState.listLabels[index]
        )
    }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(buttons = buttons)
        } ,
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        SelectedContent(selectedIndex = selectedIndex.intValue, modifier = Modifier.padding(innerPadding))
    }

}

@Composable
fun BottomNavigationBar(buttons : List<RioBottomNavItemData>) {
    RioBottomNavigation(
        fabIcon = ImageVector.vectorResource(R.drawable.ic_search_ai),
        buttons = buttons ,
        fabSize = MaterialTheme.dimens.bottomNavFabSize ,
        barHeight = MaterialTheme.dimens.bottomNavHeight ,
        selectedItemColor = MainColor ,
        fabBackgroundColor = MainColor ,
        onFabClick = {

        }
    )
}

@Composable
fun SelectedContent(selectedIndex : Int , modifier: Modifier) {
    when(selectedIndex) {
        0 -> {
            HomeScreen().Content()
        }
        1 -> {
            LibraryScreen().Content()
        }
        2 -> {
            NotificationScreen().Content()
        }
        3 -> {
            ProfileScreen().Content()
        }

    }
}

@Composable
fun ShowText(text : String) {
    Box(
        modifier = Modifier
            .fillMaxSize() ,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text ,
            style = TextStyle(
                color = DarkGray ,
                fontFamily = FontFamily(listOf(Font(R.font.publicsans_light))) ,
                fontSize = 12.sp
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreenContent()
}