package com.example.electronicjournal.di

import com.example.electronicjournal.data.network.repositories.*
import com.example.electronicjournal.domain.*
import dagger.Binds
import dagger.Module

@Module
interface AppBindModule {

    @Binds
    fun bindRemoteDatabaseRepository(repository: RemoteDatabaseRepositoryImpl): RemoteDatabaseRepository

    @Binds
    fun bindAuthorizationUseCase(repository: AuthorizationUseCaseImpl): AuthorizationUseCase

    @Binds
    fun bindGetTimetableUserCase(repository: GetCurrentTimetableUseCaseImpl): GetCurrentTimetableUseCase

    @Binds
    fun bindGetStudentsUseCase(repository: GetStudentsUseCaseImpl): GetStudentsUseCase

    @Binds
    fun bindCreateAttendanceUseCase(repository: CreateAttendanceUseCaseImpl): CreateAttendanceUseCase

    @Binds
    fun bindCurrentDatesWeekUseCase(repository: CurrentDatesWeekUseCaseImpl): CurrentDatesWeekUseCase

    @Binds
    fun bindDoneAttendanceUseCase(repository: DoneAttendanceUseCaseImpl): DoneAttendanceUseCase

    @Binds
    fun bindOpenAttendance(repository: OpenAttendanceUseCaseImpl): OpenAttendanceUseCase
}