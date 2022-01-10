package com.example.testapp.ui.view

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter

class MainListAdapter() :
    ListAdapter<String, MainViewHolder>(MainComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
    }
}