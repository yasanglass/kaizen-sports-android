package dev.yasan.kaizen.presentation.ui.preview.provider

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import dev.yasan.kaizen.model.SportEvent
import dev.yasan.kaizen.presentation.ui.preview.PreviewHelper

class SportEventPreviewProvider : PreviewParameterProvider<SportEvent> {

    override val values = PreviewHelper.sportEvents.asSequence()

}