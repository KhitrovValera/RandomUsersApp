package com.example.testapplication.domain.common

sealed class DomainException(
    val message: String,
    cause: Throwable? = null
) {
    class NetworkError(message: String = "Network error", cause: Throwable? = null) : DomainException(message, cause)
    class ServerError(message: String = "Server error", cause: Throwable? = null) : DomainException(message, cause)
    class UnknownHttpError(val httpCode: Int? = null, message: String = "An unknown HTTP error occurred", cause: Throwable? = null) : DomainException(message, cause)
    class UnknownError(message: String = "An unknown error occurred", cause: Throwable? = null) : DomainException(message, cause)
}

