package com.yuresko.lenta.utils

import com.google.android.exoplayer2.upstream.cache.SimpleCache

object Constants {

    const val BASE_URL = "https://api.tjournal.ru/v2.0/"

    const val START_URL = "https://leonardo.osnova.io/"
    const val IMG_END_URL = "/-/preview/400/-/format/webp/"
    const val GIF_END_URL = "/-/format/mp4/"

    var simpleCache: SimpleCache? = null

    const val MAX_PAGE_SIZE = 100
    const val PER_PAGE = 10
}

