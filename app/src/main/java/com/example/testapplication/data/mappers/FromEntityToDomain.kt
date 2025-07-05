package com.example.testapplication.data.mappers

import com.example.testapplication.data.local.entity.UserEntity
import com.example.testapplication.domain.model.User

fun UserEntity.toDomainUser(): User {
    return User(
        uuid = uuid,
        fullName = "$title $firstName $lastName",
        gender = gender,
        email = email,
        phone = phone,
        cell = cell,
        age = age,
        date = date,
        address = "$street, $city, $state, $country",
        coordinates = Pair(latitude, longitude),
        pictureLarge = pictureLarge,
        pictureMedium = pictureMedium,
        nationality = nationality
    )
}