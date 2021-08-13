package com.yuresko.lenta.extensions

import com.yuresko.lenta.data.models.ResponseGifv
import com.yuresko.lenta.models.ModelPost
import com.yuresko.lenta.utils.Constants.GIF_END_URL
import com.yuresko.lenta.utils.Constants.IMG_END_URL
import com.yuresko.lenta.utils.Constants.START_URL

fun ResponseGifv.toModelPost(): List<ModelPost> {
    return this.result.items.map { item ->
        ModelPost(
            id = item.data.id.toInt(),
            avatar = START_URL + item.data.subsite.avatar.data.uuid + IMG_END_URL,
            name = item.data.author.name,
            userDescription = item.data.author.description,
            subSiteName = item.data.subsite.name,
            video = START_URL + item.data.blocks?.firstOrNull()?.data?.items?.firstOrNull()?.image?.data?.uuid + GIF_END_URL,
            subSiteDescription = item.data.subsite.description,
            comments = item.data.counters.comments,
            likes = item.data.counters.favorites
        )
    }
}