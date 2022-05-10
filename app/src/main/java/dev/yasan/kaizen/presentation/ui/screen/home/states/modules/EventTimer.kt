package dev.yasan.kaizen.presentation.ui.screen.home.states.modules

import android.os.CountDownTimer
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import dev.yasan.kit.compose.foundation.grid
import dev.yasan.kit.compose.type.rubikFamily
import java.text.SimpleDateFormat
import java.util.*

/**
 * Shows a countdown timer to [timeTargetSeconds].
 *
 * @param timeFormat the format of the time to be displayed.
 */
@Composable
fun EventTimer(
    modifier: Modifier = Modifier,
    timeTargetSeconds: Long,
    timeFormat: SimpleDateFormat = SimpleDateFormat("DD:HH:mm:ss", Locale.getDefault())
) {

    val timer = remember { mutableStateOf<CountDownTimer?>(null) }
    val timerText = remember { mutableStateOf("LOADING") }

    Text(
        modifier = modifier
            .clip(MaterialTheme.shapes.small)
            .background(color = MaterialTheme.colorScheme.tertiaryContainer)
            .padding(grid())
            .widthIn(min = grid(14)),
        text = timerText.value,
        color = MaterialTheme.colorScheme.tertiary,
        fontFamily = rubikFamily,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        textAlign = TextAlign.Center
    )

    DisposableEffect(key1 = timeTargetSeconds) {

        val timeNowSeconds = System.currentTimeMillis() / 1000 // Convert to seconds
        val timeLeftMillis = (timeTargetSeconds - timeNowSeconds) * 1000// Convert to milliseconds

        timer.value = object : CountDownTimer(timeLeftMillis, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                timerText.value = timeFormat.format(millisUntilFinished)
            }

            override fun onFinish() {
                timerText.value = "STARTED"
            }

        }.apply {
            start()
        }

        onDispose {
            timer.value?.cancel()
        }

    }

}