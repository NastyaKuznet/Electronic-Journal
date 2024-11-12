package com.example.electronicjournal.di

import dagger.Module

@Module(
    includes = [
        AppBindModule::class,
        ViewModelModule::class,
        NetworkModule::class,
    ]
)
class AppModule{}