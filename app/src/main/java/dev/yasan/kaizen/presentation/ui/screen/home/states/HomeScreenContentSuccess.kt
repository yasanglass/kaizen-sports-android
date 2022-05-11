package dev.yasan.kaizen.presentation.ui.screen.home.states

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import dev.yasan.kaizen.model.Sport
import dev.yasan.kaizen.model.SportEvent
import dev.yasan.kaizen.presentation.ui.screen.home.states.modules.SportItem
import dev.yasan.kit.compose.parts.branding.YasanBrandingFooter

fun LazyListScope.homeScreenContentSuccess(
    sports: List<Sport>,
    favorites: List<SportEvent>,
    addToFavorites: (SportEvent) -> Unit = {},
    removeFromFavorites: (SportEvent) -> Unit = {}
) {

    fun isFavorite(event: SportEvent) = favorites.contains(event)

    item {
        SportItem(
            sport = Sport(
                id = "favorites",
                name = "Favorites",
                events = favorites,
            ),
            isFavorite = { isFavorite(it) },
            addToFavorites = addToFavorites,
            removeFromFavorites = removeFromFavorites,
        )
    }

    items(items = sports, key = { it.id }) { sport ->
        SportItem(
            sport = sport,
            isFavorite = { isFavorite(it) },
            addToFavorites = addToFavorites,
            removeFromFavorites = removeFromFavorites,
        )
    }

    item {
        YasanBrandingFooter()
    }

}

