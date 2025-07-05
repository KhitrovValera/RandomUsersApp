package com.example.testapplication.domain.common

sealed class Result<out T> {
    data class Success<T>(val data: T): Result<T>()
    data class Error(val domainException: DomainException): Result<Nothing>()
}