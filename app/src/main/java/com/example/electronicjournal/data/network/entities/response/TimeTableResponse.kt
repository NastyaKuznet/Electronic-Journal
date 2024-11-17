package com.example.electronicjournal.data.network.entities.response

data class TimeTableResponse(
    val weekDay: String, //какой именно тип у дня недели?
    val lessonStartTime: String,  //какой именно тип?
    val lessonEndTime: String,
    val classroom: String,
    val lesson: String,
    val nameTeacher: String,
    val lastnameTeacher: String,
)