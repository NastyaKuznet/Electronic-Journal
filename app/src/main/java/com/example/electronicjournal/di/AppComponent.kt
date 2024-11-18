package com.example.electronicjournal.di


import com.example.electronicjournal.presenter.authorization.AuthorizationFragment
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
}

