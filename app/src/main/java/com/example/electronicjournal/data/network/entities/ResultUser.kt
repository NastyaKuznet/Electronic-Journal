package com.example.electronicjournal.data.network.entities

import com.example.electronicjournal.data.module.User


data class ResultUser(
    val user: User,
    val stateResult: Boolean
)
