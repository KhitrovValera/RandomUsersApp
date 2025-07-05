package com.example.testapplication.domain.useCase

import com.example.testapplication.domain.common.Result
import com.example.testapplication.domain.model.User
import com.example.testapplication.domain.repository.UserRepository

class UpdateUsersUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): Result<List<User>> {
        return userRepository.updateUsers()
    }
}