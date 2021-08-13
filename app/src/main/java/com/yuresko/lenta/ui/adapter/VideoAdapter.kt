package com.yuresko.lenta.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.yuresko.lenta.R
import com.yuresko.lenta.models.ModelPost

class VideoAdapter : PagingDataAdapter<ModelPost, RecyclerView.ViewHolder>(DiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.item_video, parent, false)
        return VideoViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        getItem(position).let {
            if (it != null) {
                (holder as VideoViewHolder).bind(it)
            }
        }
    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<ModelPost>() {
        override fun areItemsTheSame(oldItem: ModelPost, newItem: ModelPost): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ModelPost, newItem: ModelPost): Boolean {
            return oldItem == newItem
        }
    }
}