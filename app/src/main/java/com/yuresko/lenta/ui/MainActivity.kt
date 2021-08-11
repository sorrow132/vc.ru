package com.yuresko.lenta.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.yuresko.lenta.R
import com.yuresko.lenta.ui.adapter.VideoAdapter
import com.yuresko.lenta.base.UiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

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

//        lifecycleScope.launch {
//            viewModel.posts.collectLatest(videoAdapter::submitData)
//        }

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
}