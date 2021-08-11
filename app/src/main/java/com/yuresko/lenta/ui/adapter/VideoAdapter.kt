package com.yuresko.lenta.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.yuresko.lenta.models.ModelPost

class VideoAdapter :
    PagingDataAdapter<ModelPost, RecyclerView.ViewHolder>(DiffUtilCallBack()) {
    private var data = ArrayList<VideoViewItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            VideoViewItem.ITEM_VIEW_TYPE_VIDEO -> VideoViewHolder(inflater, parent)
            else -> throw IllegalArgumentException()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val value = data[position]
        when (holder) {
            is VideoViewHolder -> holder.bind(value as VideoViewItem.VideoItem)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        return data[position].viewType
    }

    fun setData(videos: List<ModelPost>) {
        data.clear()
        data.addAll(VideoViewItem.buildItems(videos))
        notifyDataSetChanged()
    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<ModelPost>() {
        override fun areItemsTheSame(oldItem: ModelPost, newItem: ModelPost): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ModelPost, newItem: ModelPost): Boolean {
            return oldItem.name == newItem.name
                    && oldItem.name == newItem.name
        }
    }
}