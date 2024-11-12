package com.example.electronicjournal.di

import com.example.electronicjournal.data.network.services.RemoteDatabaseService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.create

@Module
class NetworkModule {
    @Provides
    fun provideRemoteDatabaseService(
        client: OkHttpClient
    ): RemoteDatabaseService =
        Retrofit.Builder()
            .client(client)
            .baseUrl("http://192.168.0.104/")
            .build()
            .create()
}