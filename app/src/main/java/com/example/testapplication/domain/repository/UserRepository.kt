package com.example.testapplication.domain.repository

import com.example.testapplication.domain.common.Result
import com.example.testapplication.domain.model.User

interface UserRepository {
    suspend fun getUsersEntry(): Result<List<User>>
    suspend fun updateUsers(): Result<List<User>>
}