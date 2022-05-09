package dev.yasan.kaizen.data.repo

import android.util.Log
import dev.yasan.kaizen.R
import dev.yasan.kaizen.data.source.network.KaizenApi
import dev.yasan.kaizen.domain.repo.SportsRepository
import dev.yasan.kaizen.model.Sport
import dev.yasan.kit.core.Resource
import retrofit2.Response
import javax.inject.Inject

/**
 * The main implementation of the [SportsRepository].
 */
class SportsRepositoryImp @Inject constructor(private val kaizenApi: KaizenApi) : SportsRepository {

    companion object {
        private const val TAG = "SportsRepositoryImp"
    }

    override suspend fun getSports(): Resource<List<Sport>> {
        val response: Response<List<Sport>>
        try {
            response = kaizenApi.getSports()
        } catch (e: Exception) {
            Log.d(TAG, "getRemoteWallpapers: ${e.message}")
            return Resource.Error(messageResourceId = R.string.error_generic)
        }
        return try {
            val body: List<Sport> = response.body()!!.filter { it.isValid() }
            Resource.Success(body)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(messageResourceId = R.string.error_generic)
        }
    }

}