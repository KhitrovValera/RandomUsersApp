package com.example.testapplication.data.repository

import com.example.testapplication.data.common.Response
import com.example.testapplication.data.local.source.UserLocalDataSource
import com.example.testapplication.data.mappers.toDomainUser
import com.example.testapplication.data.mappers.toUserEntity
import com.example.testapplication.data.remote.source.UserRemoteDataSource
import com.example.testapplication.domain.common.DomainException
import com.example.testapplication.domain.common.Result
import com.example.testapplication.domain.model.User
import com.example.testapplication.domain.repository.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiRepository: UserRemoteDataSource,
    private val dbRepository: UserLocalDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
): UserRepository {

    override suspend fun getUsersEntry(): Result<List<User>> = withContext(ioDispatcher) {
        val cachedUsers = dbRepository.getUsers()
        return@withContext if (cachedUsers.isNotEmpty()) {
            Result.Success(getUsers())
        } else {
            updateUsers()
        }
    }

    override suspend fun updateUsers(): Result<List<User>> = withContext(ioDispatcher) {
        val response = apiRepository.getUsers()
        return@withContext when (response) {
            is Response.Error -> {
                val error = response.cause
                when (error) {
                    is HttpException -> {
                        val code = error.code()
                        when (code) {
                            in 500..599 -> Result.Error(DomainException.ServerError(cause = error))
                            else -> Result.Error(
                                DomainException.UnknownHttpError(
                                    httpCode = code,
                                    cause = error
                                )
                            )
                        }
                    }

                    is IOException -> Result.Error(DomainException.NetworkError(cause = error))
                    else -> Result.Error(DomainException.UnknownError(cause = error))
                }
            }

            is Response.Success -> {
                dbRepository.clearAll()
                val users = response.data.results.map { it.toUserEntity() }
                dbRepository.insertUsers(users)
                Result.Success(getUsers())
            }
        }
    }

    private suspend fun getUsers(): List<User> = withContext(ioDispatcher) {
        val users = dbRepository.getUsers().map { it.toDomainUser() }
        return@withContext users
    }
}