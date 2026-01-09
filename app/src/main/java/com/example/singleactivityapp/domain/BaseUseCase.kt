package com.example.singleactivityapp.domain

import com.example.singleactivityapp.data.newtwork.Users
import kotlinx.coroutines.flow.Flow

interface BaseUseCase<in P, out R> {
    suspend operator fun invoke(param: P): Flow<Result<List<Users>>>
}