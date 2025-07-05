package com.example.testapplication.domain.useCase

import com.example.testapplication.domain.model.User
import com.example.testapplication.domain.repository.UserRepository
import com.example.testapplication.domain.common.Result

class GetUsersEntryUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): Result<List<User>> {
        return userRepository.getUsersEntry()
    }
}