package dev.yasan.kaizen.presentation.ui.preview.provider

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import dev.yasan.kaizen.model.Sport
import dev.yasan.kaizen.presentation.ui.preview.PreviewHelper

class SportPreviewProvider : PreviewParameterProvider<Sport> {

    override val values = PreviewHelper.sports.asSequence()

}