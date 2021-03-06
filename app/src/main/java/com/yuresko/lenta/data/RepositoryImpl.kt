package com.yuresko.lenta.data

import com.yuresko.lenta.base.ResponseResult
import com.yuresko.lenta.extensions.toModelPost
import com.yuresko.lenta.models.ModelPost
import com.yuresko.lenta.utils.DispatcherProvider
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val service: TjournalApi,
    private val dispatchers: DispatcherProvider
) : Repository {
    private val subSiteId: String = "237832"
    private val sorting: String = "week"
    private val allSite: Boolean = false

    override suspend fun getPosts(lastId: String): ResponseResult<List<ModelPost>> =
        withContext(dispatchers.io) {
            try {
                service.getData(subSiteId, sorting, allSite, lastId).body()?.toModelPost()
                    ?.let { models ->
                        ResponseResult.Success(models)
                    } ?: ResponseResult.Success(emptyList())
            } catch (ex: Exception) {
                ResponseResult.Error(ex)
            }
        }


}