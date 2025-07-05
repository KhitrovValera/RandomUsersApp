package com.example.testapplication.data.local.source

import com.example.testapplication.data.local.entity.UserEntity

interface UserLocalDataSource {
    suspend fun insertUsers(users: List<UserEntity>)
    suspend fun getUsers(): List<UserEntity>
    suspend fun clearAll()
}