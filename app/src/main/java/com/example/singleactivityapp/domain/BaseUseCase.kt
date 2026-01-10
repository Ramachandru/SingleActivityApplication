package com.example.singleactivityapp.domain

interface BaseUseCase<in P, out R> {
    suspend operator fun invoke(param: P): R
}