package com.example.electronicjournal.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.electronicjournal.presenter.authorization.AuthorizationViewModel
import com.example.electronicjournal.presenter.currentLesson.CurrentLessonViewModel
import com.example.electronicjournal.presenter.journal.JournalViewModel

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(AuthorizationViewModel::class)
    fun bindMainViewModel(vm: AuthorizationViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(JournalViewModel::class)
    fun bindJournalViewModel(vm: JournalViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CurrentLessonViewModel::class)
    fun bindCurrentLessonViewModel(vm: CurrentLessonViewModel): ViewModel
}