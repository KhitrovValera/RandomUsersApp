package com.example.testapplication.data.remote.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UsersInfo(
    @SerialName("info")
    val info: Info,
    @SerialName("results")
    val results: List<Result>
)