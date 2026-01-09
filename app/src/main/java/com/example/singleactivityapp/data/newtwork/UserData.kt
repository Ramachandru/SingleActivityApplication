package com.example.singleactivityapp.data.newtwork

data class Users(val results: List<User> = listOf())
data class User(val user: UserDetails? = null)
data class UserDetails(
    val gender: String = "",
    val email: String = "",
    val username: String = "",
    val password: String = "",
    val phone: String = ""
)
