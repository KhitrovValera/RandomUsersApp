package com.example.testapplication.data.mappers

import com.example.testapplication.data.local.entity.UserEntity
import com.example.testapplication.data.remote.model.Result

fun Result.toUserEntity() : UserEntity {
    return UserEntity(
        uuid = login.uuid,
        title = name.title,
        firstName = name.first,
        lastName = name.last,
        gender = gender,
        email = email,
        phone = phone,
        cell = cell,
        age = dob.age,
        date = dob.date.take(10),
        street = "${location.street.number} ${location.street.name}",
        city = location.city,
        state = location.state,
        country = location.country,
        postcode = location.postcode,
        latitude = location.coordinates.latitude,
        longitude = location.coordinates.longitude,
        pictureLarge = picture.large,
        pictureMedium = picture.medium,
        thumbnail = picture.thumbnail,
        nationality = nat
    )
}