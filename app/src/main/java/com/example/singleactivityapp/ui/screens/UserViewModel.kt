package com.example.singleactivityapp.ui.screens

import androidx.lifecycle.viewModelScope
import com.example.singleactivityapp.data.newtwork.User
import com.example.singleactivityapp.data.newtwork.Users
import com.example.singleactivityapp.domain.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UserViewModel @Inject constructor(private val userUseCase: UserUseCase) :
    BaseViewModel<UserViewModel.UserState, UserViewModel.UserIntent>() {
    override fun initState() = UserState()

    override suspend fun loadData() {
        userUseCase(2).collect {
            when {
                it.isSuccess -> {
                    it.getOrNull()
                        ?.let { userList -> updateUiState(uiState.value.copy(userList = userList[0])) }
                }

                it.isFailure -> {
                    updateUiState(uiState.value.copy(error = "Wrong Data"))
                }
            }

        }
    }

    override fun handleIntent(intent: UserIntent) {
        when (intent) {
            is UserIntent.IncreaseCount -> {
                viewModelScope.launch {
                    userUseCase(intent.count).collect {
                        when {
                            it.isSuccess -> {
                                it.getOrNull()
                                    ?.let { userList -> updateUiState(uiState.value.copy(userList = userList[0])) }
                            }

                            it.isFailure -> {
                                updateUiState(uiState.value.copy(error = "Wrong Data"))
                            }
                        }
                    }
                }
            }
        }
    }

    data class UserState(val userList: Users? =null , val error: String ="")
    sealed class UserIntent {
        data class IncreaseCount(val count: Int) : UserIntent()
    }
}