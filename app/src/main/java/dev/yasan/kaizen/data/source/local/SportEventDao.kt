package dev.yasan.kaizen.data.source.local

import androidx.lifecycle.LiveData
import androidx.room.*
import dev.yasan.kaizen.model.SportEvent

@Dao
interface SportEventDao {

    @Query("SELECT * FROM favorite_events")
    fun getAllFavorites(): LiveData<List<SportEvent>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(sportEvent: SportEvent)

    @Delete
    suspend fun delete(sportEvent: SportEvent)

}