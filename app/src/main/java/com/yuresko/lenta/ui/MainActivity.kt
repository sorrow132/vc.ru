package com.yuresko.lenta.ui

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.yuresko.lenta.R
import com.yuresko.lenta.ui.adapter.VideoAdapter
import com.yuresko.lenta.ui.adapter.WrapContentLinearLayoutManager
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
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        videoAdapter = VideoAdapter()
        videoRecyclerView.apply {
            layoutManager =
                WrapContentLinearLayoutManager(
                    this@MainActivity,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            adapter = videoAdapter
        }

        lifecycleScope.launch {
            viewModel.posts.collectLatest(videoAdapter::submitData)
        }

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