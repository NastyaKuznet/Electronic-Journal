package com.example.electronicjournal.presenter.journal.journalElements

data class JournalLesson(
    var id: Int,
    var timeStart: String,
    var timeEnd: String,
    var nameLesson: String,
    var typeLesson: String,
    val room: String,
    val fioTeacher: String,
    val date: String,
): JournalItem

enum class TypeLesson(val ru: String){
    LECTURE("Лекция"),
    PRACTICE("Практика"),
    NONE(""),
}