package com.example.electronicjournal.di


import dagger.Component

@Component(
    modules = [
        AppModule::class,
    ]
)
interface AppComponent {
    //abstract fun inject(fragment: MainFragment)
}

