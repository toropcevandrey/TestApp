package com.example.testapp.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.testapp.R

class FullscreenPhotoActivity : AppCompatActivity() {
    private var url: String = ""
    private lateinit var ivFullscreenPhoto: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fullscreen_photo)
        url = intent.getStringExtra("image_url").toString()
        ivFullscreenPhoto = findViewById(R.id.iv_fullscreen_photo)

        Glide.with(this)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.progress_animation)
            .error(R.drawable.ic_image_not_found)
            .into(ivFullscreenPhoto)
    }

}