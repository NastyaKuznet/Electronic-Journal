package com.example.electronicjournal.data.network.services

import com.example.electronicjournal.data.module.*
import com.example.electronicjournal.data.network.entities.requestBody.*
import com.example.electronicjournal.data.network.entities.response.*
import retrofit2.http.*

interface RemoteDatabaseService {

    @POST("/authorization")
    suspend fun authorization(@Body requestBody: AuthorizationBody): List<Student>

    @POST("/create-attendance")
    suspend fun createAttendance(@Body requestBody: CreationAttendanceBody): List<Attendance>

    @POST("/attendance-done")
    suspend fun attendanceDone(@Body requestBody: AttendanceDoneBody)

    @POST("/time-table")
    suspend fun getTimeTable(@Body requestBody: GroupIdBody): TimeTableResponse // не тестила

    @POST("/teachers")
    suspend fun getTeachers(@Body requestBody: GroupIdBody): List<Teacher>
}