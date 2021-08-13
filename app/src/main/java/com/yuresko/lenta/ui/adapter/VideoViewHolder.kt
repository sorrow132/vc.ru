package com.yuresko.lenta.ui.adapter

import android.graphics.Color
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.yuresko.lenta.R
import com.yuresko.lenta.models.ModelPost
import kotlinx.android.synthetic.main.item_video.view.*

class VideoViewHolder(view: View) : RecyclerView.ViewHolder(view), VideoPlayerEventListener {
    private var item: ModelPost? = null

    fun bind(model: ModelPost) {
        item = model

        itemView.subSiteName.text = model.subSiteName
        itemView.userName.text = model.name
        itemView.postLikes.text = model.likes.toString()

        if (model.likes > 0)
            itemView.postLikes.setTextColor(
                ContextCompat.getColor(
                    itemView.context,
                    R.color.comments_color
                )
            )
        else
            itemView.postLikes.setTextColor(Color.GRAY)


        if (model.comments > 0)
            itemView.commentsCount.text = model.comments.toString()
        else
            itemView.commentsCount.text = itemView.context.resources.getString(R.string.count_comments)


        if (model.headline.isEmpty()) {
            itemView.headingPost.visibility = View.GONE
        } else {
            itemView.headingPost.visibility = View.VISIBLE
            itemView.headingPost.text = model.headline
        }


        // Set avatar
        with(model.avatar)
        {
            Glide.with(itemView.context)
                .load(model.avatar)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(itemView.userAvatar)
        }

        // Set video preview
        with(model.video)
        {
            Glide.with(itemView.context)
                .load(model.video)
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
                itemView.itemVideoPlayerThumbnail.visibility = View.INVISIBLE
                itemView.itemVideoPlayer.visibility = View.VISIBLE
            }
        }, DELAY_BEFORE_HIDE_THUMBNAIL) // wait to be sure the texture view is render
    }

    private fun SimpleExoPlayer.playVideo() {
        stop()
        val videoUrl = item?.video ?: return
        setMediaItem(MediaItem.fromUri(videoUrl))
        prepare()
    }

    companion object {
        private const val DELAY_BEFORE_HIDE_THUMBNAIL = 500L
    }
}
