package com.yuresko.lenta.models

data class ModelPost(
    val id: Int,
    val avatar: String,
    val name: String,
    val headline: String,
    val subSiteName: String,
    val video: String,
    val subSiteDescription: String,
    val likes: Int,
    val comments: Int,
)