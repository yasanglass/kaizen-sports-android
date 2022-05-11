package dev.yasan.kaizen.domain.repo

import androidx.lifecycle.LiveData
import dev.yasan.kaizen.model.SportEvent

interface FavoritesRepository {

    fun getAllFavorites(): LiveData<List<SportEvent>>

    suspend fun addToFavorites(event: SportEvent)

    suspend fun removeFromFavorites(event: SportEvent)

}