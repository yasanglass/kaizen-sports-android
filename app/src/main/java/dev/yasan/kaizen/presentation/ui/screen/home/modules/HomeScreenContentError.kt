package dev.yasan.kaizen.presentation.ui.screen.home.modules

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.yasan.kaizen.presentation.ui.common.KaizenError

@Preview(name = "Home Content: Error")
@Composable
fun HomeScreenContentError(onRetry: () -> Unit = {}) {

    KaizenError(onClick = onRetry)

}