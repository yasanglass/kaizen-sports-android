package dev.yasan.kaizen.presentation.ui.screen.home.modules

import androidx.compose.animation.*
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.rounded.ExpandMore
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import dev.yasan.kaizen.model.Sport
import dev.yasan.kaizen.presentation.ui.preview.provider.SportPreviewProvider
import dev.yasan.kaizen.presentation.ui.theme.KaizenIcons
import dev.yasan.kit.compose.foundation.grid
import dev.yasan.kit.compose.type.rubikFamily

fun LazyListScope.HomeScreenContentSuccess(sports: List<Sport>) {

    items(items = sports) { sport ->
        SportItem(sport = sport)
    }

}

@Preview(name = "Sport Item")
@Composable
fun SportItem(@PreviewParameter(SportPreviewProvider::class) sport: Sport) {

    val expanded = rememberSaveable {
        mutableStateOf(false)
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
            // TODO show events
        }

    }

}