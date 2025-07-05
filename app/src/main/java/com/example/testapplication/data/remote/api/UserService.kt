package com.example.testapplication.data.remote.api

import com.example.testapplication.data.common.Response
import com.example.testapplication.data.remote.model.UsersInfo
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {
    @GET("./")
    suspend fun getUsers(@Query("results") count: Int = 10): UsersInfo
}