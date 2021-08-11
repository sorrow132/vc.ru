package com.yuresko.lenta.data

import com.yuresko.lenta.base.ResponseResult
import com.yuresko.lenta.models.ModelPost

interface Repository {
    suspend fun getModels(): ResponseResult<List<ModelPost>>
}