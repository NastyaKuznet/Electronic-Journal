package com.example.electronicjournal.data.module

data class Lesson(
    val id: Int,
    val week_day: String,
    val number_week: Int,
    val lesson_start_time: String,
    val lesson_end_time: String,
    val classroom: String,
    val lesson: String,
    val type_lesson: String,
)