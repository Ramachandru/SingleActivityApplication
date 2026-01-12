package com.example.singleactivityapp.domain.repository

import com.example.singleactivityapp.data.Users
import com.example.singleactivityapp.data.newtwork.ApiInterface
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(private val apiCall: ApiInterface) : UserRepository {
    override suspend fun getUserList(count: Int): Users? {
        return apiCall.getRandomUserResults(count)
    }
}