package com.example.testapplication.data.remote.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Street(
    @SerialName("name")
    val name: String,
    @SerialName("number")
    val number: Int
)