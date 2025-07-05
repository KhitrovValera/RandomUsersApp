package com.example.testapplication.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapplication.domain.common.DomainException
import com.example.testapplication.domain.common.Result
import com.example.testapplication.domain.model.User
import com.example.testapplication.domain.useCase.UserListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userListUseCase: UserListUseCase
): ViewModel() {

    private val _state = MutableStateFlow<State>(State.Loading)
    val state: StateFlow<State> = _state

    private val _actions = MutableSharedFlow<Action>()
    val actions: SharedFlow<Action> = _actions

    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users

    init {
        loadUsers { userListUseCase.getUsersEntry.invoke() }
    }

    fun onRefreshButtonPressed() {
        loadUsers { userListUseCase.updateUsers.invoke() }
    }

    fun onUserCardPressed(user: User) {
        viewModelScope.launch {
            _actions.emit(Action.RouteToUser(user))
        }
    }

    private fun loadUsers(useCase: suspend () -> Result<List<User>>) {
        _state.value = State.Loading

        viewModelScope.launch {
            val response = useCase()
            handleResponse(response)
        }
    }

    private fun handleResponse(response: Result<List<User>>) {
        when (response) {
            is Result.Success -> {
                _state.value = State.Active
                _users.value = response.data
            }
            is Result.Error -> {
                var message = response.domainException.message
                if (response.domainException is DomainException.UnknownHttpError) message += response.domainException.httpCode.toString()
                _state.value = State.Error(message)
            }
        }
    }


    sealed interface State {
        object Loading : State
        object Active : State
        data class Error(val reason: String): State
    }

    sealed interface Action {
        data class RouteToUser(val user: User) : Action
    }
}