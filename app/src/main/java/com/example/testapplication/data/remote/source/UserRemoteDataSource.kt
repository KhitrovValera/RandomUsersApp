package com.example.testapplication.data.remote.source

import com.example.testapplication.data.common.Response
import com.example.testapplication.data.remote.model.UsersInfo

interface UserRemoteDataSource {
    suspend fun getUsers(): Response<UsersInfo>
}