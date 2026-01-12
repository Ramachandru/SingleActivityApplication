package com.example.singleactivityapp.domain.usecase

import com.example.singleactivityapp.data.Users
import com.example.singleactivityapp.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserUseCase @Inject constructor(private val repository: UserRepository) :
    BaseUseCase<Int, Flow<Result<Users>>> {
    override suspend fun invoke(param: Int): Flow<Result<Users>> = flow {
        val response = repository.getUserList(param)
        response?.let {
            emit(Result.success(response))
        } ?: run {
            emit(Result.failure(Exception("Error in Api:")))
        }
    }
}