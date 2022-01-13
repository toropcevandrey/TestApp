package com.example.testapp.ui.viewmodel

sealed class PhotosState {
    class Success(val photos: List<String>) : PhotosState()
    object Error : PhotosState()
    object Loading : PhotosState()
}
