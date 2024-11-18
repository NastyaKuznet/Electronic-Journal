package com.example.electronicjournal.presenter.models

import com.example.electronicjournal.data.module.User

data class ResultUIUser(
    val user: User,
    val resultUIState: ResultUIState,
)
