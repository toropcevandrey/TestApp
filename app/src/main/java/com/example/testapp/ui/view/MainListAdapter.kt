package com.example.testapp.ui.view

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter

class MainListAdapter(private val screenWidth: Int) :
    ListAdapter<String, MainViewHolder>(MainComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder.create(parent, screenWidth)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }
}