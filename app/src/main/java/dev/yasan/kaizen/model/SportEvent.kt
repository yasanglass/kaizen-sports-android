package dev.yasan.kaizen.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "favorite_events")
@JsonClass(generateAdapter = true)
data class SportEvent(
    @field:Json(name = "i") @PrimaryKey(autoGenerate = false) val id: String,
    @field:Json(name = "d") val name: String,
    @field:Json(name = "si") val sportId: String,
    @field:Json(name = "tt") val startTime: Long,
) {

    /**
     * Checks if the object's data is valid.
     * The object should not be shown to the user if it is invalid.
     */
    fun isValid() = id.isNotBlank() && name.isNotBlank() && sportId.isNotBlank() && startTime > 0

    /**
     * Splits [name] into two (or more) parts to separate the name of the teams.
     */
    fun getSplitName() = name.split(" - ")

}