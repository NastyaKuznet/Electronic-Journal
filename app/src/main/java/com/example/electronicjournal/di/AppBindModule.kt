package com.example.electronicjournal.di

import com.example.electronicjournal.data.network.repositories.*
import com.example.electronicjournal.domain.AuthorizationUseCase
import com.example.electronicjournal.domain.AuthorizationUseCaseImpl
import dagger.Binds
import dagger.Module

@Module
interface AppBindModule {

    @Binds
    fun bindRemoteDatabaseRepository(repository: RemoteDatabaseRepositoryImpl): RemoteDatabaseRepository

    @Binds
    fun bindAuthorizationUseCase(repository: AuthorizationUseCaseImpl): AuthorizationUseCase
}