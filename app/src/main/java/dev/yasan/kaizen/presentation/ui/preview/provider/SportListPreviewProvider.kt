package dev.yasan.kaizen.presentation.ui.preview.provider

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import dev.yasan.kaizen.model.Sport
import dev.yasan.kaizen.presentation.ui.preview.PreviewHelper

class SportListPreviewProvider : PreviewParameterProvider<List<Sport>> {

    override val values = sequenceOf(PreviewHelper.sports)

}