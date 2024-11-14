package com.example.electronicjournal.di


import com.example.electronicjournal.presenter.MainFragment
import dagger.Component

@Component(
    modules = [
        AppModule::class,
    ]
)
interface AppComponent {
    abstract fun inject(fragment: MainFragment)
}

