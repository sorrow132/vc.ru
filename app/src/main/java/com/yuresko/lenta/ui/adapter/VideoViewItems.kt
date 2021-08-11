package com.yuresko.lenta.ui.adapter

import androidx.paging.PagingData
import com.yuresko.lenta.models.ModelPost
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

sealed class VideoViewItem(val viewType: Int) {
    data class VideoItem(val data: ModelPost) : VideoViewItem(ITEM_VIEW_TYPE_VIDEO)
    companion object {
        const val ITEM_VIEW_TYPE_VIDEO = 2

        fun buildItems(videos: List<ModelPost>): ArrayList<VideoViewItem> {
            return arrayListOf<VideoViewItem>().apply {
                videos.map { add(VideoItem(it)) }
            }
        }
    }
}
