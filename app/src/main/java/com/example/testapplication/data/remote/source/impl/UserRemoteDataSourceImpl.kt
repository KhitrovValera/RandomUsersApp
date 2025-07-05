package com.example.testapplication.data.remote.source.impl

import android.util.Log
import com.example.testapplication.data.common.Response
import com.example.testapplication.data.remote.api.UserService
import com.example.testapplication.data.remote.model.UsersInfo
import com.example.testapplication.data.remote.source.UserRemoteDataSource
import retrofit2.HttpException
import javax.inject.Inject

class UserRemoteDataSourceImpl @Inject constructor(
    private val api: UserService
) : UserRemoteDataSource {
    override suspend fun getUsers(): Response<UsersInfo> {
        return try {
            val response = api.getUsers(count = 20)
            Response.Success(response)
        } catch (e: HttpException) {
            Response.Error(e)
        } catch (e: java.io.IOException) {
            Response.Error(e)
        } catch (e: Exception) {
            Response.Error(e)
        }
    }
}