package com.example.singleactivityapp.data.newtwork

import kotlinx.serialization.Serializable

@Serializable
data class Users(val results: List<User> = listOf())

@Serializable
data class User(val user: UserDetails? = null)

@Serializable
data class UserDetails(
    val gender: String = "",
    val email: String = "",
    val username: String = "",
    val password: String = "",
    val phone: String = ""
)
