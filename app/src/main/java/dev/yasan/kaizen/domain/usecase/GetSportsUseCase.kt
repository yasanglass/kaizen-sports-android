package dev.yasan.kaizen.domain.usecase

import dev.yasan.kaizen.domain.repo.SportsRepository
import dev.yasan.kit.core.Resource
import javax.inject.Inject


class GetSportsUseCase @Inject constructor(
    private val sportsRepository: SportsRepository
) {

    suspend operator fun invoke() = sportsRepository.getSports()

}