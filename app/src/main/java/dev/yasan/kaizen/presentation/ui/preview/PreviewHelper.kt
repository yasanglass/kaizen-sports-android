package dev.yasan.kaizen.presentation.ui.preview

import dev.yasan.kaizen.model.Sport
import dev.yasan.kaizen.model.SportEvent

/**
 * Helper object for Compose previews. Holds all dummy data in one place.
 * All preview providers should use the data from this object.
 */
object PreviewHelper {

    val sportEventsFoot = listOf(
        SportEvent(
            id = "24456069",
            name = "Medeama SC - Dreams FC",
            sportId = "FOOT",
            startTime = 1668925680,
        ),
        SportEvent(
            id = "24439615",
            name = "Abahani Chittagong - Mohammedan SC",
            sportId = "FOOT",
            startTime = 1670068380,
        )
    )

    val sportEventsTenn = listOf(
        SportEvent(
            id = "24457652",
            name = "Tomas Machac - Andrea Arnaboldi",
            sportId = "TENN",
            startTime = 1646543400,
        ),
        SportEvent(
            id = "24439615",
            name = "Caroline Garcia - Barbora Krejcikova",
            sportId = "TENN",
            startTime = 1672054500,
        )
    )

    val sportFoot = Sport(
        id = "FOOT",
        name = "SOCCER",
        events = sportEventsFoot
    )

    val sportTenn = Sport(
        id = "TENN",
        name = "TENNIS",
        events = sportEventsTenn
    )

    val sports = listOf(sportFoot, sportTenn)

    val sportEvents = sportEventsFoot + sportEventsTenn

}