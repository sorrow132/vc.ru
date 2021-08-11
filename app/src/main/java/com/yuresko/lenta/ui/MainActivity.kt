package com.yuresko.lenta.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yuresko.lenta.R
import com.yuresko.lenta.base.UiState
import com.yuresko.lenta.ui.adapter.VideoAdapter
import com.yuresko.lenta.ui.adapter.VideoViewHolder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var videoAdapter: VideoAdapter

    private val viewModel: LentaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        videoAdapter = VideoAdapter()
        videoRecyclerView.apply {
            adapter = videoAdapter
        }

        subscribeUi()
    }

    private fun subscribeUi() {
        viewModel.data.observe(this, { state ->
            when (state) {
                is UiState.Error -> {
                    progressBar.visibility = View.GONE
                    Log.e("Error state", state.error.message.toString())
                }
                UiState.Loading -> {
                    progressBar.visibility = View.VISIBLE
                }
                is UiState.Success -> {
                    progressBar.visibility = View.GONE
                    videoAdapter.setData(state.data)
                }
            }
        })
    }

    override fun onPause() {
        super.onPause()
        videoRecyclerView.changePlayingState(false)
    }

    override fun onResume() {
        super.onResume()
        videoRecyclerView.changePlayingState(true)
    }

}