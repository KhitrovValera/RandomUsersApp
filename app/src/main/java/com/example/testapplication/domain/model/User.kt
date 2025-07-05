package com.example.testapplication.domain.model

data class User(
    val uuid: String,
    val fullName: String,
    val gender: String,
    val email: String,
    val phone: String,
    val cell: String,
    val age: Int,
    val date: String,
    val address: String,
    val coordinates: Pair<String, String>,
    val pictureLarge: String,
    val pictureMedium: String,
    val nationality: String
)