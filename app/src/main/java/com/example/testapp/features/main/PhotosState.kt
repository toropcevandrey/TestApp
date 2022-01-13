package com.example.testapp.features.main

sealed class PhotosState {
    class Success(val photos: List<String>) : PhotosState()
    object Error : PhotosState()
    object Loading : PhotosState()
}
