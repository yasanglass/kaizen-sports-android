package dev.yasan.kaizen.domain.repo

import dev.yasan.kaizen.model.Sport
import dev.yasan.kit.core.Resource

interface SportsRepository {

    /**
     * Fetches a list of sports.
     * Handles error and loading states by returning a [Resource] object.
     */
    suspend fun getSports(): Resource<List<Sport>>

}