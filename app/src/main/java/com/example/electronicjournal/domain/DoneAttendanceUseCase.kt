package com.example.electronicjournal.domain

import com.example.electronicjournal.data.module.Attendance
import com.example.electronicjournal.data.network.entities.requestBody.AttendanceStudentBody
import com.example.electronicjournal.data.network.repositories.RemoteDatabaseRepository
import com.example.electronicjournal.presenter.models.StudentAttention
import javax.inject.Inject

interface DoneAttendanceUseCase {

    suspend operator fun invoke(attendanceId: Int, listAttendance: List<StudentAttention>): Boolean
}

class DoneAttendanceUseCaseImpl @Inject constructor(
    private val remoteDatabaseRepository: RemoteDatabaseRepository,
): DoneAttendanceUseCase {

    override suspend fun invoke(attendanceId: Int, listAttendance: List<StudentAttention>): Boolean {
        val listAtt = mutableListOf<AttendanceStudentBody>()
        for(el in listAttendance){
            listAtt.add(AttendanceStudentBody(el.student.id,
                when(el.attention){
                    "+" -> 3
                    "Н" -> 1
                    else -> 2
                }))
        }
        return remoteDatabaseRepository.attendanceDone(attendanceId, listAtt)
    }
}