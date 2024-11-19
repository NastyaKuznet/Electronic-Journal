package com.example.electronicjournal.data.network.repositories

import com.example.electronicjournal.data.module.Attendance
import com.example.electronicjournal.data.module.Lesson
import com.example.electronicjournal.data.module.Student
import com.example.electronicjournal.data.module.Teacher
import com.example.electronicjournal.data.network.entities.ResultUser
import com.example.electronicjournal.data.network.entities.requestBody.*
import com.example.electronicjournal.data.network.services.RemoteDatabaseService
import retrofit2.HttpException
import javax.inject.Inject

interface RemoteDatabaseRepository {

    suspend fun authorization(email: String, password: String): ResultUser
    suspend fun createAttendance(classId: Int, timedate: String): Attendance
    suspend fun attendanceDone(attendanceId: Int, students: List<AttendanceStudentBody>): Boolean //
    suspend fun getTimeTable(groupId: Int): List<Lesson>
    suspend fun getTeachers(groupId: Int): List<Teacher>
}

class RemoteDatabaseRepositoryImpl @Inject constructor(
    private val service: RemoteDatabaseService,
): RemoteDatabaseRepository {

    override suspend fun authorization(email: String, password: String): ResultUser {
        try {
            val user = service.authorization(AuthorizationBody(email, password))
            if(user.isEmpty()){
                return ResultUser(
                    Student(
                        0,
                        "",
                        "",
                        "",
                        "",
                        "",
                        false,
                        0
                    ), false)
            }
            return ResultUser(user[0], true)
        } catch (e: HttpException){
            if (e.code() == 404){
                return ResultUser(
                    Student(
                    0,
                    "",
                    "",
                    "",
                    "",
                    "",
                    false,
                    0
                ), false)
            }
            throw e
        }
    }

    override suspend fun createAttendance(classId: Int, timedate: String): Attendance {
        try {
            val result = service.createAttendance(CreationAttendanceBody(classId, timedate))
            return result[0]
        } catch (e: HttpException){
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

    override suspend fun getTimeTable(groupId: Int): List<Lesson> {
        try {
            return service.getTimeTable(GroupIdBody(groupId))
        } catch (e: HttpException){
            throw e
        }
    }

    override suspend fun getTeachers(groupId: Int): List<Teacher> {
        try {
            return service.getTeachers(GroupIdBody(groupId))
        } catch (e: HttpException){
            throw e
        }
    }
}