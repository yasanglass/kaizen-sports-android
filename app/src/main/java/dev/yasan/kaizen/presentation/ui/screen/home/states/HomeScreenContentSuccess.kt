package dev.yasan.kaizen.presentation.ui.screen.home.states

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import dev.yasan.kaizen.model.Sport
import dev.yasan.kaizen.presentation.ui.screen.home.states.modules.SportItem
import dev.yasan.kit.compose.foundation.grid

fun LazyListScope.homeScreenContentSuccess(sports: List<Sport>) {

    items(items = sports) { sport ->
        SportItem(sport = sport)
    }

    item {
        Spacer(modifier = Modifier.requiredHeight(grid(8)))
    }

}

