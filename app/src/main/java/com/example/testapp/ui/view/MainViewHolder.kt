package com.example.testapp.ui.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testapp.R

open class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val iv: ImageView = itemView.findViewById(R.id.iv_ImageView)

    fun bind(image: String) {
        Glide.with(itemView)
            .load(image)
            .into(iv)
    }

    companion object {
        fun create(parent: ViewGroup, screenWidth: Int): MainViewHolder {
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview, parent, false)
                view.updateLayoutParams {
                    width = screenWidth / 2
                }

            return MainViewHolder(view)
        }
    }
}