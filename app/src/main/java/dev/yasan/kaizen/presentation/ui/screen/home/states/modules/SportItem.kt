package dev.yasan.kaizen.presentation.ui.screen.home.states.modules

import androidx.compose.animation.*
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.rounded.ExpandMore
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.sp
import dev.yasan.kaizen.R
import dev.yasan.kaizen.model.Sport
import dev.yasan.kaizen.model.SportEvent
import dev.yasan.kaizen.presentation.ui.preview.provider.SportPreviewProvider
import dev.yasan.kaizen.presentation.ui.theme.KaizenIcons
import dev.yasan.kit.compose.foundation.grid
import dev.yasan.kit.compose.type.rubikFamily

@Preview(name = "Sport Item")
@Composable
fun SportItem(
    @PreviewParameter(SportPreviewProvider::class) sport: Sport,
    isFavorite: (SportEvent) -> Boolean = { false },
    addToFavorites: (SportEvent) -> Unit = {},
    removeFromFavorites: (SportEvent) -> Unit = {}
) {

    val expanded = rememberSaveable {
        mutableStateOf(true)
    }

    val buttonAngle by animateFloatAsState(if (expanded.value) 180f else 0f)

    Column(modifier = Modifier.fillMaxWidth()) {

        Row(
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.surfaceVariant)
                .fillMaxWidth()
                .padding(horizontal = grid(1.5f), vertical = grid(0.5f)),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                modifier = Modifier.weight(1f),
                text = sport.name,
                fontFamily = rubikFamily,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )

            Icon(
                modifier = Modifier
                    .rotate(buttonAngle)
                    .clip(CircleShape)
                    .clickable {
                        expanded.value = !expanded.value
                    }
                    .padding(grid()),
                imageVector = KaizenIcons.ExpandMore,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )

        }

        Divider()

        AnimatedVisibility(
            visible = expanded.value,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically()
        ) {

            LazyRow(
                modifier = Modifier
                    .animateContentSize()
                    .fillMaxWidth()
                    .padding(vertical = grid())
            ) {

                item {
                    Spacer(modifier = Modifier.requiredWidth(grid(0.5f)))
                }

                items(items = sport.events, key = { it.id }) { event ->
                    EventItem(
                        modifier = Modifier.padding(start = grid(1.5f)),
                        event = event,
                        isFavorite = isFavorite(event),
                        addToFavorites = {
                            addToFavorites(event)
                        },
                        removeFromFavorites = {
                            removeFromFavorites(event)
                        }
                    )
                }

                item {
                    if (sport.events.isEmpty()) {
                        Text(
                            modifier = Modifier.padding(grid(2)),
                            text = stringResource(R.string.no_events),
                            color = MaterialTheme.colorScheme.error,
                            fontFamily = rubikFamily,
                            fontSize = 16.sp,
                            fontStyle = FontStyle.Italic
                        )
                    }
                }

                item {
                    Spacer(modifier = Modifier.requiredWidth(grid(8)))
                }

            }

        }

    }

}