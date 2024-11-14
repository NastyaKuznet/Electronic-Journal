package com.example.electronicjournal.data.network.entities.requestBody

data class AttendanceDoneBody(
    val attendanceId: Int,
    val students: List<AttendanceStudentBody>,
)
