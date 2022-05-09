package dev.yasan.kaizen.presentation.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.yasan.kaizen.presentation.ui.screen.home.HomeScreen
import dev.yasan.kaizen.presentation.ui.theme.KaizenTheme

/**
 * The top level composable for the application.
 */
@Composable
fun KaizenApp() {
    KaizenTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            HomeScreen()
        }
    }
}