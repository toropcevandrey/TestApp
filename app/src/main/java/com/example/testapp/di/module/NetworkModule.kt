package com.example.testapp.di.module

import com.example.testapp.api.RetrofitBuilder
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofitBuilder() = RetrofitBuilder.apiService
}