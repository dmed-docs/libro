package uz.luka.libro.presantation.screens.intro

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import androidx.compose.material3.MaterialTheme
import cafe.adriel.voyager.hilt.getViewModel
import kotlinx.coroutines.launch
import uz.luka.libro.presantation.components.app.ButtonUi
import uz.luka.libro.presantation.components.intro.IndicatorUi
import uz.luka.libro.presantation.components.intro.IntroTopUi
import uz.luka.libro.ui.theme.dimens

class IntroScreen : Screen {
    @Composable
    override fun Content() {
        val viewModel = getViewModel<IntroViewModel>()

        IntroScreenContent(
            viewModel.uiState.collectAsState().value ,
            viewModel::onEventDispatchers
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun IntroScreenContent(
    uiState : IntroContract.UiState = IntroContract.UiState(),
    onEventDispatchers : (IntroContract.Intent) -> Unit = {}
) {
    val pagerState = rememberPagerState(initialPage = 0) {
        uiState.pages.size
    }

    val buttonState = remember {
        derivedStateOf {
            when(pagerState.currentPage) {
                0 -> listOf("" , "Next")
                1 -> listOf("Back" , "Next")
                2 -> listOf("Back" , "Finish")
                else -> listOf("" , "")
            }
        }
    }

    val scope = rememberCoroutineScope()

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = MaterialTheme.dimens.large)
        ) {
            HorizontalPager(state = pagerState) { index ->
                IntroTopUi(introCommon = uiState.pages[index])
            }
        }

        Column (
            modifier = Modifier.align(Alignment.BottomCenter)
        ){
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = MaterialTheme.dimens.paddingMedium, vertical = MaterialTheme.dimens.paddingTiny),
                horizontalArrangement = Arrangement.SpaceBetween ,
                verticalAlignment = Alignment.CenterVertically
            ){

                Box(
                    modifier = Modifier.weight(1f) ,
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (buttonState.value[0].isNotEmpty()) {
                        ButtonUi(
                            text = "Back" ,
                            textColor = Color.Black ,
                            borderSize = 2 ,
                            backgroundColor = Color.White
                        ) {
                            scope.launch {
                                if(pagerState.currentPage > 0) {
                                    pagerState.animateScrollToPage(pagerState.currentPage - 1)
                                }
                            }
                        }
                    }
                }

                Box(
                    modifier = Modifier.weight(1f) ,
                    contentAlignment = Alignment.Center
                ) {
                    IndicatorUi(pageSize = uiState.pages.size, currentSize = pagerState.currentPage)
                }


                Box(
                    modifier = Modifier.weight(1f) ,
                    contentAlignment = Alignment.CenterEnd
                ) {
                    ButtonUi(
                        text = buttonState.value[1]
                    ) {
                        scope.launch {
                            if(pagerState.currentPage < uiState.pages.size - 1) {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            } else {
                                onEventDispatchers(IntroContract.Intent.MoveToSignInScreen)
                            }
                        }
                    }
                }


            }

            Spacer(modifier = Modifier.size(MaterialTheme.dimens.spacingExtraLarge))
        }
    }





}

@Preview(showBackground = true)
@Composable
fun IntroScreenPreview() {
    IntroScreenContent()
}