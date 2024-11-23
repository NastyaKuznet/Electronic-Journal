package com.example.electronicjournal.domain

import com.example.electronicjournal.data.module.Student
import com.example.electronicjournal.data.network.repositories.RemoteDatabaseRepository
import javax.inject.Inject

interface GetStudentsUseCase {

    suspend operator fun invoke(groupId: Int): List<Student>
}

class GetStudentsUseCaseImpl @Inject constructor(
    private val remoteDatabaseRepository: RemoteDatabaseRepository
): GetStudentsUseCase {

    override suspend fun invoke(groupId: Int): List<Student> {
        return remoteDatabaseRepository.getStudents(groupId)
    }
}