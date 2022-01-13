package com.example.testapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapp.data.api.ApiService
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {

    var photosListEvent: MutableLiveData<PhotosState> = MutableLiveData(PhotosState.Loading)

    init {
        loadPhotos()
    }

    fun loadPhotos() {
        viewModelScope.launch {
            try {
                photosListEvent.value = PhotosState.Loading
                photosListEvent.value = PhotosState.Success(apiService.getImages())
            } catch (e: Exception) {
                photosListEvent.value = PhotosState.Error
            }
        }
    }
}