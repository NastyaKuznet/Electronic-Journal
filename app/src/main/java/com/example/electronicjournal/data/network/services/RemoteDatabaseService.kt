package com.example.electronicjournal.data.network.services

import com.example.electronicjournal.data.module.*
import com.example.electronicjournal.data.network.entities.requestBody.*
import retrofit2.http.*

interface RemoteDatabaseService {

    @POST("/authorization")
    suspend fun authorization(@Body requestBody: AuthorizationBody): List<Student>

    @POST("/create-attendance")
    suspend fun createAttendance(@Body requestBody: CreationOpenAttendanceBody): List<Attendance>

    @POST("/time-table")
    suspend fun getTimeTable(@Body requestBody: GroupIdBody): List<Lesson>

    @POST("/teachers")
    suspend fun getTeachers(@Body requestBody: GroupIdBody): List<Teacher>

    @POST("/students")
    suspend fun getStudents(@Body requestBody: GroupIdBody): List<Student>

    @POST("/attendancedone")
    suspend fun attendanceDone(@Body requestBody: AttendanceDoneBody)

    @POST("/open-attendance")
    suspend fun attendanceOpen(@Body requestBody: CreationOpenAttendanceBody): List<AttendanceStudent>
}