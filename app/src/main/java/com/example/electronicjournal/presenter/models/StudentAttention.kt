package com.example.electronicjournal.presenter.models

import com.example.electronicjournal.data.module.Student

data class StudentAttention(
    val student: Student,
    var attention: String = "",
)