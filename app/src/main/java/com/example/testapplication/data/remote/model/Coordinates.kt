package com.example.testapplication.data.remote.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Coordinates(
    @SerialName("latitude")
    val latitude: String,
    @SerialName("longitude")
    val longitude: String
)