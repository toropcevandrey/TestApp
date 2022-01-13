package com.example.testapp.api

import retrofit2.http.GET

interface ApiService {
    @GET("https://dev-tasks.alef.im/task-m-001/list.php")
    suspend fun getImages(): List<String>
}