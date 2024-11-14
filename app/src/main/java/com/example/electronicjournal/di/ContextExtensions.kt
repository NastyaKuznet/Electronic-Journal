package com.example.electronicjournal.di

import android.content.Context
import com.example.electronicjournal.MyApplication


val Context.appComponent: AppComponent
    get() = when(this) {
        is MyApplication -> appComponent
        else -> applicationContext.appComponent
    }