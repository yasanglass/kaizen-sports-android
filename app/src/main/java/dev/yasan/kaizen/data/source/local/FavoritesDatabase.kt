package dev.yasan.kaizen.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.yasan.kaizen.model.SportEvent
import javax.inject.Inject

@Database(
    entities = [SportEvent::class],
    version = 1
)
abstract class FavoritesDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "favorite_events"
    }

    abstract fun sportEventDao(): SportEventDao

    class CallBack @Inject constructor() : RoomDatabase.Callback()

}

