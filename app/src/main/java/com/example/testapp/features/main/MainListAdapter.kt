package com.example.testapp.features.main

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter

class MainListAdapter(
    private val photoSize: Int,
    private val onClickListener: OnClickListener
) :
    ListAdapter<String, MainViewHolder>(MainComparator()) {

    interface OnClickListener {
        fun onClick(url: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder.create(parent, photoSize)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
        holder.itemView.setOnClickListener() {
            onClickListener.onClick(current)
        }
    }
}