package com.example.singleactivityapp.di

import com.example.singleactivityapp.domain.repository.UserRepository
import com.example.singleactivityapp.domain.repository.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindUserRepository(usr: UserRepositoryImpl): UserRepository
}