package dev.yasan.kaizen.data.repo

import androidx.lifecycle.LiveData
import dev.yasan.kaizen.data.source.local.SportEventDao
import dev.yasan.kaizen.domain.repo.FavoritesRepository
import dev.yasan.kaizen.model.SportEvent
import javax.inject.Inject

/**
 * The main implementation of the [FavoritesRepository].
 */
class FavoritesRepositoryImp @Inject constructor(private val dao: SportEventDao) :
    FavoritesRepository {

    override fun getAllFavorites(): LiveData<List<SportEvent>> = dao.getAllFavorites()

    override suspend fun addToFavorites(event: SportEvent) {
        dao.insert(event)
    }

    override suspend fun removeFromFavorites(event: SportEvent) {
        dao.delete(event)
    }

}