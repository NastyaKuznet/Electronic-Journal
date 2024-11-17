package com.example.electronicjournal.data.network.services

import com.example.electronicjournal.data.network.entities.requestBody.*
import com.example.electronicjournal.data.network.entities.response.*
import retrofit2.http.*

interface RemoteDatabaseService {

    @GET("/authorization")
    suspend fun authorization(@Body requestBody: AuthorizationBody)

    @GET("/attendance-done")
    suspend fun attendanceDone(@Body requestBody: AttendanceDoneBody)

    @GET("/time-table")
    suspend fun getTimeTable(@Body requestBody: GroupIdBody): TimeTableResponse

    @GET("/teachers")
    suspend fun getTeachers(@Body requestBody: GroupIdBody): TeachersBody
}