package com.example.electronicjournal.domain

import com.example.electronicjournal.data.module.AttendanceStudent
import com.example.electronicjournal.data.module.Student
import com.example.electronicjournal.data.network.repositories.RemoteDatabaseRepository
import javax.inject.Inject

interface OpenAttendanceUseCase {
    suspend operator fun invoke(classId: Int, timedate: String): List<AttendanceStudent>
}

class OpenAttendanceUseCaseImpl @Inject constructor(
    val remoteDatabaseRepository: RemoteDatabaseRepository,
): OpenAttendanceUseCase {
    override suspend fun invoke(classId: Int, timedate: String): List<AttendanceStudent> {
        return remoteDatabaseRepository.openAttendance(classId, timedate)
    }

}
