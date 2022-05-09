package dev.yasan.kaizen.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Sport(
    @field:Json(name = "i") val id: String,
    @field:Json(name = "d") val name: String,
    @field:Json(name = "e") val events: List<SportEvent>,
) {

    /**
     * Checks if the object's data is valid.
     * The object should not be shown to the user if it is invalid.
     */
    fun isValid() = id.isNotBlank() && name.isNotBlank() && events.isNotEmpty() && events.all { it.isValid() }

}