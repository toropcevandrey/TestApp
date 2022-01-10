package com.example.testapp

import android.app.Application
import com.example.testapp.di.component.AppComponent
import com.example.testapp.di.component.DaggerAppComponent

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder().build()
    }

    companion object {
        private lateinit var component: AppComponent

        fun getComponent(): AppComponent {
            return component
        }
    }
}