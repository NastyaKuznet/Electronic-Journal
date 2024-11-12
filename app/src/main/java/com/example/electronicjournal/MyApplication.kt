package com.example.electronicjournal

import android.app.Application
import com.example.electronicjournal.di.AppComponent
import com.example.electronicjournal.di.DaggerAppComponent

class MyApplication: Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        appComponent = DaggerAppComponent
            .create()

        super.onCreate()
    }
}