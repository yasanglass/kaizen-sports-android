package dev.yasan.kaizen.presentation.ui.screen.home.states.modules

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.StarBorder
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.yasan.kaizen.model.SportEvent
import dev.yasan.kaizen.presentation.ui.preview.provider.SportEventPreviewProvider
import dev.yasan.kaizen.presentation.ui.theme.KaizenIcons
import dev.yasan.kit.compose.foundation.grid
import dev.yasan.kit.compose.type.rubikFamily

/**
 * Displays a [SportEvent] item.
 *
 * @see EventTimer
 */
@Composable
fun EventItem(
    modifier: Modifier = Modifier,
    event: SportEvent,
    isFavorite: Boolean,
    addToFavorites: () -> Unit = {},
    removeFromFavorites: () -> Unit = {}
) {

    Column(
        modifier = modifier
            .padding(vertical = grid(1.5f))
            .border(
                shape = MaterialTheme.shapes.large,
                color = MaterialTheme.colorScheme.outline,
                width = 1.dp
            )
            .clip(MaterialTheme.shapes.medium)
            .padding(grid(2))
            .widthIn(min = grid(20)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        EventTimer(timeTargetSeconds = event.startTime)

        val icon = if (isFavorite) KaizenIcons.Star else KaizenIcons.StarBorder
        val iconTint =
            if (isFavorite) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.onSurface

        Icon(
            modifier = Modifier
                .padding(vertical = grid(0.5f))
                .clip(CircleShape)
                .clickable {
                    if (isFavorite) {
                        removeFromFavorites()
                    } else {
                        addToFavorites()
                    }
                }
                .padding(grid()),
            imageVector = icon,
            contentDescription = null,
            tint = iconTint
        )

        event.getSplitName().forEachIndexed { index, name ->
            if (index != 0) {
                Spacer(modifier = Modifier.requiredHeight(grid(0.5f)))
            }
            Text(
                text = name,
                color = MaterialTheme.colorScheme.onSurface,
                fontFamily = rubikFamily,
                fontSize = 12.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

    }

}

@Preview(name = "Sport Event Item", showBackground = true)
@Composable
private fun EventItemPreview(
    @PreviewParameter(SportEventPreviewProvider::class) event: SportEvent
) {
    EventItem(
        modifier = Modifier.padding(horizontal = grid(2)),
        event = event,
        isFavorite = false
    )
}

@Preview(name = "Sport Event Item (Favorite)", showBackground = true)
@Composable
private fun EventItemPreviewFavorite(
    @PreviewParameter(SportEventPreviewProvider::class) event: SportEvent
) {
    EventItem(
        modifier = Modifier.padding(horizontal = grid(2)),
        event = event,
        isFavorite = true
    )
}