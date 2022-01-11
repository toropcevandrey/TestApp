package com.example.testapp.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowMetrics
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.testapp.App
import com.example.testapp.R
import com.example.testapp.ui.viewmodel.MainViewModel
import javax.inject.Inject

class MainActivity () : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    @Inject
    lateinit var screenUtils: ScreenUtils
    private var viewModel: MainViewModel? = null
    private lateinit var rvMain: RecyclerView
    private lateinit var adapterMain: MainListAdapter
    private lateinit var swipeRefresh: SwipeRefreshLayout
    private var screenWidth: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        App.getComponent().inject(this)
        setupMainViewModel()
        initViews()
        setObservers()
        swipeRefresh.setOnRefreshListener {
            viewModel?.firstInit()
            swipeRefresh.isRefreshing = false
        }
    }

    private fun setupMainViewModel() {
        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
        firstCreate()
    }

    private fun firstCreate() {
        viewModel?.firstInit()
    }

    private fun initViews() {
        screenWidth = screenUtils.getScreenWidth(this)
        swipeRefresh = findViewById(R.id.swipe_refresh)
        rvMain = findViewById(R.id.rv)
        rvMain.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        adapterMain = MainListAdapter(screenWidth)
        rvMain.adapter = adapterMain
        rvMain.layoutManager = GridLayoutManager(this, 2)


    }

    private fun setObservers() {
        viewModel?.imagesData?.observe(this) { images ->
            adapterMain.submitList(images)
        }
    }
}