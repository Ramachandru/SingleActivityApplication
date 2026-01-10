package com.example.singleactivityapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.singleactivityapp.ui.navigation.AppNavHost
import com.example.singleactivityapp.ui.navigation.NavRouteDestination
import com.example.singleactivityapp.ui.screens.BottomBarNavigation
import com.example.singleactivityapp.ui.theme.SingleActivityAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val navController = rememberNavController()
            val navBackStackEntry = navController.currentBackStackEntryAsState()
            val destination = navBackStackEntry.value?.destination

            SingleActivityAppTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {},
                    bottomBar = { BottomBarNavigation(navController) }) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        AppNavHost(
                            navHostController = navController,
                            startDestination = NavRouteDestination.AllGender,
                            modifier = Modifier
                        )
                    }
                }
            }
        }
    }
}