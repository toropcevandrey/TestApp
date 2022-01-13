package com.example.testapp.ui.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.testapp.R

open class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val iv: ImageView = itemView.findViewById(R.id.iv_photo)

    fun bind(image: String) {
        Glide.with(itemView)
            .load(image)
            .placeholder(R.drawable.progress_animation)
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .error(R.drawable.ic_image_not_found)
            .into(iv)
    }

    companion object {
        fun create(parent: ViewGroup, screenWidth: Int, screenHeight: Int): MainViewHolder {
            val view: View = LayoutInflater.from(parent.context)
                .inflate(R.layout.view_holder, parent, false)
            val x: Int = if (screenHeight > screenWidth) {
                screenWidth / 2
            } else {
                screenWidth / 3
            }
            view.updateLayoutParams {
                width = x
                height = x
            }
            return MainViewHolder(view)
        }
    }
}