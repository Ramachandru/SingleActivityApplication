package com.example.singleactivityapp.domain

import com.example.singleactivityapp.data.newtwork.ApiInterface
import com.example.singleactivityapp.data.newtwork.Users
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserUseCase @Inject constructor(private val apiCall: ApiInterface) :
    BaseUseCase<Int, Flow<Result<List<Users>>>> {
    override suspend fun invoke(param: Int): Flow<Result<List<Users>>> = flow {
        val response = apiCall.getRandomUserResults(param)
        response?.let {
            emit(Result.success(listOf(response)))
        } ?: run {
            emit(Result.failure(Exception("Error in Api:")))
        }
    }
}