package com.example.testapplication.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val uuid: String,
    val title: String,
    val firstName: String,
    val lastName: String,
    val gender: String,
    val email: String,
    val phone: String,
    val cell: String,
    val age: Int,
    val date: String,
    val street: String,
    val city: String,
    val state: String,
    val country: String,
    val postcode: String,
    val latitude: String,
    val longitude: String,
    val pictureLarge: String,
    val pictureMedium: String,
    val thumbnail: String,
    val nationality: String
)
