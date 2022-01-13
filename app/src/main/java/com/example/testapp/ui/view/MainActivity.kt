package com.example.testapp.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.testapp.App
import com.example.testapp.R
import com.example.testapp.ui.OnClickListener
import com.example.testapp.ui.viewmodel.MainViewModel
import com.example.testapp.ui.viewmodel.PhotosState
import javax.inject.Inject

class MainActivity() : AppCompatActivity() {

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        App.getComponent().inject(this)
        setupMainViewModel()
        initViews()
        setObservers()
        swipeRefresh.setOnRefreshListener {
            viewModel?.loadPhotos()
            swipeRefresh.isRefreshing = false
        }
    }

    private fun setupMainViewModel() {
        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
    }

    private fun initViews() {
        swipeRefresh = findViewById(R.id.swipe_refresh)
        rvMain = findViewById(R.id.rv)
        tvError = findViewById(R.id.tv_error)
        btnRefresh = findViewById(R.id.btn_refresh)
        btnRefresh.setOnClickListener {
            viewModel?.loadPhotos()
        }
        adapterMain = MainListAdapter(
            screenUtils.getScreenWidth(this),
            screenUtils.getScreenHeight(this),
            OnClickListener { url ->
                openFullscreenPhoto(url)
            })
        rvMain.adapter = adapterMain
        rvMain.layoutManager =
            if (screenUtils.getScreenHeight(this) > screenUtils.getScreenWidth(this)) {
                GridLayoutManager(this, 2)
            } else {
                GridLayoutManager(this, 3)
            }
    }

    private fun setObservers() {
        viewModel?.photosListEvent?.observe(this) { state ->
            if (state is PhotosState.Success) {
                adapterMain.submitList(state.photos)
                rvMain.isVisible = true
                tvError.isVisible = false
                btnRefresh.isVisible = false
            } else {
                rvMain.isVisible = false
                tvError.isVisible = true
                btnRefresh.isVisible = true
            }
        }
    }

    private fun openFullscreenPhoto(url: String) {
        intent = Intent(this, FullscreenPhotoActivity::class.java)
        intent.putExtra("image_url", url)
        startActivity(intent)
    }
}