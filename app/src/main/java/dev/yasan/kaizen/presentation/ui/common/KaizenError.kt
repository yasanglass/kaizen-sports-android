package dev.yasan.kaizen.presentation.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import dev.yasan.kaizen.R
import dev.yasan.kaizen.presentation.ui.theme.KaizenIcons
import dev.yasan.kit.compose.foundation.grid
import dev.yasan.kit.compose.type.rubikFamily

/**
 * All errors should be presented using this composable.
 *
 * @param title The error's title text.
 * @param onClick The callback to be invoked when the user clicks the error button.
 */
@Preview()
@Composable
fun KaizenError(
    modifier: Modifier = Modifier,
    title: String = stringResource(id = R.string.error_generic),
    onClick: () -> Unit = {}
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.surfaceVariant)
            .padding(grid(2)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = title,
            fontFamily = rubikFamily,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.requiredHeight(grid(2)))

        Button(onClick = onClick) {
            Icon(
                modifier = Modifier,
                imageVector = KaizenIcons.Refresh,
                contentDescription = null,
            )
            Spacer(modifier = Modifier.requiredWidth(grid(0.5f)))
            Text(
                modifier = Modifier,
                text = title.uppercase(),
                fontFamily = rubikFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
        }

    }
}