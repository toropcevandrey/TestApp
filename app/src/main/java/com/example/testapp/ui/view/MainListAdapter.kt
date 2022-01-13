package com.example.testapp.ui.view

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.testapp.ui.OnClickListener

class MainListAdapter(
    private val screenWidth: Int,
    private val screenHeight: Int,
    private val onClickListener: OnClickListener
) :
    ListAdapter<String, MainViewHolder>(MainComparator()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder.create(parent, screenWidth, screenHeight)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current)
        holder.itemView.setOnClickListener() {
            onClickListener.onClick(current)
        }
    }
}