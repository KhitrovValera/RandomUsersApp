package com.example.testapplication.domain.useCase

data class UserListUseCase(
    val getUsersEntry: GetUsersEntryUseCase,
    val updateUsers: UpdateUsersUseCase
)