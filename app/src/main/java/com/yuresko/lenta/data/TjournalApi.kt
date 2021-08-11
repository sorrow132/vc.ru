package com.yuresko.lenta.data

import com.yuresko.lenta.data.models.ResponseGifv
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TjournalApi {

    @GET("timeline")
    suspend fun getData(
        @Query("subsitesIds") subsitesIds: String,
        @Query("sorting") sorting: String,
        @Query("allSite") allSite: Boolean,
        @Query("lastId") lastId: String
    ): Response<ResponseGifv>
}