package com.example.singleactivityapp.ui.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import androidx.savedstate.SavedState
import com.example.singleactivityapp.data.User
import com.example.singleactivityapp.ui.screens.AllGenderList
import com.example.singleactivityapp.ui.screens.FemaleListScreen
import com.example.singleactivityapp.ui.screens.MaleListScreen
import com.example.singleactivityapp.ui.screens.UserDetails
import kotlinx.serialization.json.Json
import kotlin.reflect.typeOf

@Composable
fun AppNavHost(modifier: Modifier, navHostController: NavHostController, startDestination: Any) {
    NavHost(
        navController = navHostController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable<NavRouteDestination.AllGender> {
            AllGenderList {
                navHostController.navigate(NavRouteDestination.UserDetails(it))
            }
        }
        composable<NavRouteDestination.Male> {
            MaleListScreen {
                navHostController.navigate(NavRouteDestination.UserDetails(it))
            }
        }
        composable<NavRouteDestination.Female> {
            FemaleListScreen {
                navHostController.navigate(NavRouteDestination.UserDetails(it))
            }
        }
        composable<NavRouteDestination.UserDetails>(typeMap = mapOf(typeOf<User>() to userNavType))
        { entry ->
            val userDetails = entry.toRoute<NavRouteDestination.UserDetails>()
            UserDetails(userDetails.user)
        }
    }
}

val userNavType = object : NavType<User>(isNullableAllowed = false) {
    override fun put(
        bundle: SavedState,
        key: String,
        value: User
    ) {
        bundle.putString(key, Json.encodeToString(value))
    }

    override fun get(
        bundle: SavedState,
        key: String
    ): User? {
        return Json.decodeFromString(bundle.getString(key) ?: return null)
    }

    override fun parseValue(value: String): User {
        return Json.decodeFromString(Uri.decode(value))
    }

    override fun serializeAsValue(value: User): String {
        return Uri.encode(Json.encodeToString(value))
    }
}