package dev.yasan.kaizen.presentation.ui.screen.home.states

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import dev.yasan.kaizen.model.Sport
import dev.yasan.kaizen.presentation.ui.screen.home.states.modules.SportItem

fun LazyListScope.homeScreenContentSuccess(sports: List<Sport>) {

    items(items = sports) { sport ->
        SportItem(sport = sport)
    }

}

