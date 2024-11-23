package com.example.electronicjournal.di


import com.example.electronicjournal.presenter.currentLesson.CurrentLessonFragment
import com.example.electronicjournal.presenter.RootFragment
import com.example.electronicjournal.presenter.authorization.AuthorizationFragment
import com.example.electronicjournal.presenter.journal.JournalFragment
import dagger.Component

@Component(
    modules = [
        AppBindModule::class,
        ViewModelModule::class,
        NetworkModule::class,
    ]
)
interface AppComponent {
    abstract fun inject(fragment: AuthorizationFragment)
    abstract fun inject(fragment: RootFragment)
    abstract fun inject(fragment: JournalFragment)
    abstract fun inject(fragment: CurrentLessonFragment)
}

