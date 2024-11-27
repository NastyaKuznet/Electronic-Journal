package com.example.electronicjournal.di

import com.example.electronicjournal.data.network.services.RemoteDatabaseService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    fun provideIntercomService(): RemoteDatabaseService =
        Retrofit.Builder()
            .baseUrl("http://185.104.249.229:3000")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RemoteDatabaseService::class.java)
}