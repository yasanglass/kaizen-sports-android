package dev.yasan.kaizen.domain.usecase

import dev.yasan.kaizen.domain.repo.FavoritesRepository
import javax.inject.Inject

/**
 * Fetches a list of favorite sport events.
 */
class GetFavoritesUseCase @Inject constructor(
    private val favoritesRepository: FavoritesRepository
) {

    operator fun invoke() = favoritesRepository.getAllFavorites()

}