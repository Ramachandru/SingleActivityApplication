package com.example.singleactivityapp.ui.screens

import androidx.lifecycle.viewModelScope
import com.example.singleactivityapp.data.User
import com.example.singleactivityapp.domain.usecase.UserUseCase
import com.example.singleactivityapp.ui.screens.base.BaseViewModel
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
                        ?.let { userList ->
                            updateUiState(
                                uiState.value.copy(
                                    genderList = userList.results,
                                    error = ""
                                )
                            )
                        }
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
                                    ?.let { userList ->
                                        updateUiState(
                                            uiState.value.copy(
                                                genderList = userList.results,
                                                error = ""
                                            )
                                        )
                                    }
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

    suspend fun getGenderListUser(gender: String = "female") {
        userUseCase(5).collect {
            when {
                it.isSuccess -> {
                    it.getOrNull()
                        ?.let { userList ->
                            val list = userList.results.filter { value ->
                                value.user?.gender.equals(
                                    gender,
                                    ignoreCase = true
                                )
                            }
                            updateUiState(uiState.value.copy(genderList = list, error = ""))
                        }
                }

                it.isFailure -> {
                    updateUiState(uiState.value.copy(error = "Wrong Data"))
                }
            }

        }
    }

    data class UserState(
        val genderList: List<User>? = listOf(),
        val error: String = "",
    )

    sealed class UserIntent {
        data class IncreaseCount(val count: Int) : UserIntent()
    }
}