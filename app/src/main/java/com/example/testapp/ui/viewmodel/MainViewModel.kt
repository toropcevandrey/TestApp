package com.example.testapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapp.data.api.ApiService
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {

    val imagesData: MutableLiveData<List<String>> = MutableLiveData()

    fun firstInit() {
        viewModelScope.launch {
            imagesData.value = apiService.getImages()
        }
    }
}