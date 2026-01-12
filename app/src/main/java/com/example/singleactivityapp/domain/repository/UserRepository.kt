package com.example.singleactivityapp.domain.repository

import com.example.singleactivityapp.data.Users

interface UserRepository {
    suspend fun getUserList(count: Int): Users?
}