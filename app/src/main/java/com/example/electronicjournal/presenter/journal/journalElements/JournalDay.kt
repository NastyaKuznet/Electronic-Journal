package com.example.electronicjournal.presenter.journal.journalElements

data class JournalDay(
    var nameDay: String,
): JournalItem

enum class Day(val ru: String){
    MONDAY("Понедельник"),
    TUESDAY("Вторник"),
    WEDNESDAY("Среда"),
    THURSDAY("Четверг"),
    FRIDAY("Пятница"),
    SATURDAY("Суббота"),
    SUNDAY("Воскресенье"),
}
