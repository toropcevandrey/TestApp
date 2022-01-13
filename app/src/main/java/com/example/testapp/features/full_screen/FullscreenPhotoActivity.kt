package com.example.testapp.features.full_screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.testapp.R
import com.github.chrisbanes.photoview.PhotoView

class FullscreenPhotoActivity : AppCompatActivity() {

    companion object{
        const val IMAGE_URL_KEY = "image_url"
    }

    private var url: String = ""
    private lateinit var ivFullscreenPhoto: PhotoView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fullscreen_photo)
        url = intent.getStringExtra(IMAGE_URL_KEY).toString()
        ivFullscreenPhoto = findViewById(R.id.iv_fullscreen_photo)

        Glide.with(this)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.progress_animation)
            .error(R.drawable.ic_image_not_found)
            .into(ivFullscreenPhoto)
    }

}