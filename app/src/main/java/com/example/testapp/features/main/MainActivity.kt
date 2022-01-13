package com.example.testapp.features.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.testapp.App
import com.example.testapp.R
import com.example.testapp.features.full_screen.FullscreenPhotoActivity
import javax.inject.Inject

class MainActivity() : AppCompatActivity(), MainListAdapter.OnClickListener {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    @Inject
    lateinit var screenUtils: ScreenUtils
    private var viewModel: MainViewModel? = null
    private lateinit var rvMain: RecyclerView
    private lateinit var adapterMain: MainListAdapter
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private lateinit var tvError: TextView
    private lateinit var btnRefresh: Button
    private lateinit var pgLoading: ProgressBar

    companion object {
        const val LANDSCAPE_COUNT = 3
        const val PORTRAIT_COUNT = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        App.getComponent().inject(this)
        setupMainViewModel()
        initViews()
        setObservers()
        swipeRefresh.setOnRefreshListener {
            viewModel?.loadPhotos()
        }
    }

    override fun onClick(url: String) {
        openFullscreenPhoto(url)
    }

    private fun setupMainViewModel() {
        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
    }

    private fun initViews() {
        swipeRefresh = findViewById(R.id.swipe_refresh)
        rvMain = findViewById(R.id.rv)
        tvError = findViewById(R.id.tv_error)
        pgLoading = findViewById(R.id.pg_loading)
        btnRefresh = findViewById(R.id.btn_refresh)
        btnRefresh.setOnClickListener {
            viewModel?.loadPhotos()
        }
        val screenWidth = screenUtils.getScreenWidth(this)
        val screenHeight = screenUtils.getScreenHeight(this)
        val photoSize: Int = if (screenHeight > screenWidth) {
            screenWidth / PORTRAIT_COUNT
        } else {
            screenWidth / LANDSCAPE_COUNT
        }
        adapterMain = MainListAdapter(photoSize, this)
        rvMain.adapter = adapterMain
        rvMain.layoutManager =
            if (screenHeight > screenWidth) {
                GridLayoutManager(this, PORTRAIT_COUNT)
            } else {
                GridLayoutManager(this, LANDSCAPE_COUNT)
            }
    }

    private fun setObservers() {
        viewModel?.photosListEvent?.observe(this) { state ->
            val isError = state is PhotosState.Error
            val isLoading = state is PhotosState.Loading
            val isSuccess = state is PhotosState.Success
            btnRefresh.isVisible = isError
            tvError.isVisible = isError
            pgLoading.isVisible = isLoading
            rvMain.isVisible = isSuccess
            swipeRefresh.isRefreshing = false

            if (isSuccess) {
                adapterMain.submitList((state as PhotosState.Success).photos)
            }
        }
    }

    private fun openFullscreenPhoto(url: String) {
        intent = Intent(this, FullscreenPhotoActivity::class.java)
        intent.putExtra(FullscreenPhotoActivity.IMAGE_URL_KEY, url)
        startActivity(intent)
    }
}