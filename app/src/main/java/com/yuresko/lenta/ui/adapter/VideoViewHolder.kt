package com.yuresko.lenta.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.dsilvera.videolist.VideoPlayerEventListener
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.yuresko.lenta.R
import kotlinx.android.synthetic.main.item_video.view.*

class VideoViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_video, parent, false)),
    VideoPlayerEventListener {
    private var item: VideoViewItem.VideoItem? = null

    fun bind(model: VideoViewItem.VideoItem) {
        item = model

        itemView.subSiteName.text = model.data.subSiteName
        itemView.userName.text = model.data.name
        itemView.commentsCount.text = model.data.comments.toString()
        itemView.postLikes.text = model.data.likes.toString()

        // Set avatar
        with(model.data) {
            Glide.with(itemView.context)
                .load(model.data.avatar)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(itemView.userAvatar)
        }

        // Set video
        with(model.data) {
            Glide.with(itemView.context)
                .load(model.data.video)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(itemView.itemVideoPlayerThumbnail)

        }
    }

    override fun onPrePlay(player: SimpleExoPlayer) {
        itemView.itemVideoPlayer.visibility = View.GONE
        itemView.itemVideoPlayerThumbnail.visibility = View.VISIBLE
        //play video
        with(player) {
            playVideo()
            itemView.itemVideoPlayer.player = this
        }
    }

    override fun onPlayCanceled() {
        itemView.itemVideoPlayer.player = null
        itemView.itemVideoPlayer.visibility = View.GONE
        itemView.itemVideoPlayerThumbnail.visibility = View.VISIBLE
    }

    override fun onPlay() {
        itemView.postDelayed({
            if (itemView.itemVideoPlayer.player != null) {
                itemView.itemVideoPlayer.visibility = View.VISIBLE
                itemView.itemVideoPlayerThumbnail.visibility = View.INVISIBLE
            }
        }, DELAY_BEFORE_HIDE_THUMBNAIL) // wait to be sure the texture view is render
    }


    private fun SimpleExoPlayer.playVideo() {
        stop(true)
        val videoUrl = item?.data?.video ?: return
        setMediaItem(MediaItem.fromUri(videoUrl))
        prepare()
    }

    companion object {
        private const val DELAY_BEFORE_HIDE_THUMBNAIL = 500L
    }
}
