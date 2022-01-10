package com.example.testapp.di.component

import com.example.testapp.di.module.NetworkModule
import com.example.testapp.di.module.ViewModelModule
import com.example.testapp.ui.view.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelModule::class, NetworkModule::class])
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}