package com.example.electronicjournal.di


import com.example.electronicjournal.presenter.RootFragment
import com.example.electronicjournal.presenter.authorization.AuthorizationFragment
import com.example.electronicjournal.presenter.journal.JournalFragment
import com.example.electronicjournal.presenter.journal.journalElements.JournalItem
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
}

