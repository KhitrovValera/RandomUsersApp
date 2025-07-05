package com.example.testapplication.data.local.source.impl

import com.example.testapplication.data.local.database.dao.UserDao
import com.example.testapplication.data.local.entity.UserEntity
import com.example.testapplication.data.local.source.UserLocalDataSource
import javax.inject.Inject

class UserLocalDataSourceImpl @Inject constructor(
    private val dao: UserDao
): UserLocalDataSource {
    override suspend fun insertUsers(users: List<UserEntity>) {
        dao.insertUsers(users)
    }

    override suspend fun getUsers(): List<UserEntity> {
        return dao.getUsers()
    }

    override suspend fun clearAll() {
        dao.clearAll()
    }
}