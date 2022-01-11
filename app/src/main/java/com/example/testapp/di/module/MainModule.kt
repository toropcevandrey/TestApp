package com.example.testapp.di.module

import com.example.testapp.ui.view.ScreenUtils
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MainModule {

    @Provides
    @Singleton
    fun provideScreenUtils() = ScreenUtils()
}