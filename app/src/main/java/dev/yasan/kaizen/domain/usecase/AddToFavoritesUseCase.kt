package dev.yasan.kaizen.domain.usecase

import dev.yasan.kaizen.domain.repo.FavoritesRepository
import dev.yasan.kaizen.model.SportEvent
import javax.inject.Inject

class AddToFavoritesUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) {

    suspend operator fun invoke(event: SportEvent) = favoritesRepository.addToFavorites(event)

}