package com.example.testapplication.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.testapplication.data.local.database.dao.UserDao
import com.example.testapplication.data.local.entity.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1
)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}