package com.example.testapplication.data.common

sealed class Response<out T> {
    data class Success<T>(val data: T): Response<T>()
    data class Error(val cause: Throwable? = null): Response<Nothing>()
}