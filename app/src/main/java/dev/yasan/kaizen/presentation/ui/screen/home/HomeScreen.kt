package dev.yasan.kaizen.presentation.ui.screen.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import dev.yasan.kaizen.R
import dev.yasan.kaizen.model.Sport
import dev.yasan.kaizen.model.SportEvent
import dev.yasan.kaizen.presentation.ui.common.KaizenScreen
import dev.yasan.kaizen.presentation.ui.preview.PreviewHelper
import dev.yasan.kaizen.presentation.ui.screen.home.states.HomeScreenContentError
import dev.yasan.kaizen.presentation.ui.screen.home.states.HomeScreenContentLoading
import dev.yasan.kaizen.presentation.ui.screen.home.states.homeScreenContentSuccess
import dev.yasan.kit.core.Resource

/**
 * The main and only screen of the app.
 */
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = hiltViewModel()
) {

    val sports = homeViewModel.sports.observeAsState(initial = Resource.Initial())
    val favorites = homeViewModel.favorites.observeAsState(initial = emptyList())

    LaunchedEffect(key1 = sports.value) {
        if (sports.value is Resource.Initial) {
            homeViewModel.loadSports()
        }
    }

    HomeScreenContent(
        modifier = modifier,
        sports = sports.value,
        favorites = favorites.value,
        addToFavorites = {
            homeViewModel.addToFavorites(it)
        },
        removeFromFavorites = {
            homeViewModel.removeFromFavorites(it)
        },
        onRetry = {
            homeViewModel.loadSports()
        }
    )

}

@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    sports: Resource<List<Sport>>,
    favorites: List<SportEvent>,
    addToFavorites: (SportEvent) -> Unit = {},
    removeFromFavorites: (SportEvent) -> Unit = {},
    onRetry: () -> Unit = {}
) {

    KaizenScreen(modifier = modifier, title = stringResource(id = R.string.app_name)) {

        when (sports) {
            is Resource.Success -> {
                homeScreenContentSuccess(
                    sports = sports.data ?: emptyList(),
                    favorites = favorites,
                    addToFavorites = addToFavorites,
                    removeFromFavorites = removeFromFavorites
                )
            }
            is Resource.Error -> {
                item {
                    HomeScreenContentError(onRetry = onRetry)
                }
            }
            else -> {
                item {
                    HomeScreenContentLoading()
                }
            }
        }

    }

}

@Preview(name = "Home Content: Success", showBackground = true)
@Composable
private fun HomeScreenContentPreviewSuccess() {
    HomeScreenContent(
        sports = Resource.Success(data = PreviewHelper.sports),
        favorites = emptyList()
    )
}

