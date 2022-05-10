package dev.yasan.kaizen.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SportEvent(
    @field:Json(name = "i") val id: String,
    @field:Json(name = "d") val name: String,
    @field:Json(name = "si") val sportId: String,
    @field:Json(name = "tt") val startTime: Long,
    @Transient
    val favorite: MutableState<Boolean> = mutableStateOf(false)
) {

    /**
     * Checks if the object's data is valid.
     * The object should not be shown to the user if it is invalid.
     */
    fun isValid() = id.isNotBlank() && name.isNotBlank() && sportId.isNotBlank() && startTime > 0

    val nameSplit = name.split(" - ")


}