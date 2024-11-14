package com.example.electronicjournal.data.network.repositories

import com.example.electronicjournal.data.network.entities.requestBody.*
import com.example.electronicjournal.data.network.entities.response.*
import com.example.electronicjournal.data.network.services.RemoteDatabaseService
import retrofit2.HttpException
import javax.inject.Inject

interface RemoteDatabaseRepository {

    suspend fun authorization(email: String, password: String): Boolean
    suspend fun attendanceDone(attendanceId: Int, students: List<AttendanceStudentBody>): Boolean //
    suspend fun getTimeTable(groupId: Int): TimeTableResponse //
    suspend fun getTeachers(groupId: Int): TeachersBody //
}

class RemoteDatabaseRepositoryImpl @Inject constructor(
    private val service: RemoteDatabaseService,
): RemoteDatabaseRepository {
    override suspend fun authorization(email: String, password: String): Boolean {
        try {
            service.authorization(AuthorizationBody(email, password))
            return true
        } catch (e: HttpException){
            if (e.code() == 404){
                return false
            }
            throw e
        }
    }

    override suspend fun attendanceDone(
        attendanceId: Int,
        students: List<AttendanceStudentBody>
    ): Boolean {
        try {
            service.attendanceDone(AttendanceDoneBody(attendanceId, students))
            return true
        } catch (e: HttpException){
            if (e.code() == 404){
                return false
            }
            throw e
        }
    }

    override suspend fun getTimeTable(groupId: Int): TimeTableResponse {
        try {
            return service.getTimeTable(GroupIdBody(groupId))
        } catch (e: HttpException){
            throw e
        }
    }

    override suspend fun getTeachers(groupId: Int): TeachersBody {
        try {
            return service.getTeachers(GroupIdBody(groupId))
        } catch (e: HttpException){
            throw e
        }
    }

}