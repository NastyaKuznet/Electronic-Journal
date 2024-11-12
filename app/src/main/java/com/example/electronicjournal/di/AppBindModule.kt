package com.example.electronicjournal.di

import com.example.electronicjournal.data.network.repositories.*
import dagger.Binds
import dagger.Module

@Module
interface AppBindModule {

    @Binds
    fun bindRemoteDatabaseRepository(repository: RemoteDatabaseRepositoryImpl): RemoteDatabaseRepository
}