package com.example.testapp.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.App
import com.example.testapp.R
import com.example.testapp.ui.viewmodel.MainViewModel
import javax.inject.Inject

class MainActivity() : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private var viewModel: MainViewModel? = null
    private lateinit var rvMain: RecyclerView
    private lateinit var adapterMain: MainListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        App.getComponent().inject(this)
        setupMainViewModel()
        initViews()
        setObservers()
    }

    private fun setupMainViewModel() {
        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
        firstCreate()
    }

    private fun firstCreate() {
        viewModel?.firstInit()
    }

    private fun initViews() {
        rvMain = findViewById(R.id.rv)
        rvMain.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        adapterMain = MainListAdapter()
        rvMain.adapter = adapterMain
        rvMain.layoutManager = LinearLayoutManager(this)
    }

    private fun setObservers() {
        viewModel?.imagesData?.observe(this) { images ->
            adapterMain.submitList(images)
        }
    }
}