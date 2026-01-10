package com.example.singleactivityapp.ui.navigation

import com.example.singleactivityapp.R
import com.example.singleactivityapp.data.newtwork.User
import kotlinx.serialization.Serializable

@Serializable
sealed class NavRouteDestination(val title: String = "", val icon: Int? = null) {
    @Serializable
    data object AllGender : NavRouteDestination("AllGender",R.drawable.male1)

    @Serializable
    data object Male : NavRouteDestination("Male", R.drawable.male1)

    @Serializable
    data object Female : NavRouteDestination("Female", R.drawable.female1)

    @Serializable
    data class UserDetails(val user: User) : NavRouteDestination("Details")
}

val bottomBar = listOf(
    NavRouteDestination.AllGender,
    NavRouteDestination.Female,
    NavRouteDestination.Male
)