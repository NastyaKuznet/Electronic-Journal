package com.example.electronicjournal.domain

import com.example.electronicjournal.data.module.Attendance
import com.example.electronicjournal.data.network.repositories.RemoteDatabaseRepository
import javax.inject.Inject

interface CreateAttendanceUseCase {

    suspend operator fun invoke(classId: Int, timedate: String): Attendance
}

class CreateAttendanceUseCaseImpl @Inject constructor(
    private val remoteDatabaseRepository: RemoteDatabaseRepository
): CreateAttendanceUseCase {

    override suspend fun invoke(classId: Int, timedate: String): Attendance {
        return remoteDatabaseRepository.createAttendance(classId, timedate)
    }
}