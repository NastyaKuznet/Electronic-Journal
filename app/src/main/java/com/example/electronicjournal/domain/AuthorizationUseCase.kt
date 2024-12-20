package com.example.electronicjournal.domain

import com.example.electronicjournal.data.network.entities.ResultUser
import com.example.electronicjournal.data.network.repositories.*
import javax.inject.Inject

interface AuthorizationUseCase {

    suspend operator fun invoke(email: String, password: String): ResultUser
}

class AuthorizationUseCaseImpl @Inject constructor(
    private val remoteDatabaseRepository: RemoteDatabaseRepository
): AuthorizationUseCase{
    override suspend fun invoke(email: String, password: String): ResultUser {
        return remoteDatabaseRepository.authorization(email, password)
    }

}