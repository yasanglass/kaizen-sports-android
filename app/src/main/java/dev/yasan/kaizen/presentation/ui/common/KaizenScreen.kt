package dev.yasan.kaizen.presentation.ui.common

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

/**
 * A simple vertical scrolling screen composable.
 *
 * @param content The content to display in the [LazyColumn]
 *
 * @see KaizenTitle
 */
@Composable
fun KaizenScreen(
    modifier: Modifier = Modifier,
    title: String,
    state: LazyListState = rememberLazyListState(),
    content: LazyListScope.() -> Unit
) {

    Column(modifier = modifier.fillMaxSize()) {

        KaizenTitle(title = title)

        LazyColumn(
            modifier = Modifier
                .animateContentSize()
                .fillMaxWidth()
                .weight(1f),
            state = state,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            content()

        }
    }

}
