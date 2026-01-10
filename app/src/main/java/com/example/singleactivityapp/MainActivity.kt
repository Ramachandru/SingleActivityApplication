package com.example.singleactivityapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.singleactivityapp.ui.navigation.AppNavHost
import com.example.singleactivityapp.ui.navigation.NavRouteDestination
import com.example.singleactivityapp.ui.screens.BottomBarNavigation
import com.example.singleactivityapp.ui.theme.SingleActivityAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val navController = rememberNavController()
            SingleActivityAppTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
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