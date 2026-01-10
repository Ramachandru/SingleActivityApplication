package com.example.singleactivityapp.ui.screens


import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.singleactivityapp.ui.navigation.bottomBar
import com.example.singleactivityapp.ui.theme.nonSelectedImg
import com.example.singleactivityapp.ui.theme.selectedImg

@Composable
fun BottomBarNavigation(navHostController: NavHostController) {
    val navBackStackEntry = navHostController.currentBackStackEntryAsState()
    val destination = navBackStackEntry.value?.destination
    NavigationBar {

        bottomBar.forEach { item ->
            val selected = destination?.hierarchy?.any() {
                it.hasRoute(item::class)
            } == true
            NavigationBarItem(
                selected = selected,
                label = {
                    Text(text = item.title)
                },
                icon = {
                    Icon(
                        painterResource(item.icon!!),
                        modifier = Modifier
                            .height(50.dp)
                            .width(50.dp),
                        contentDescription = item.title,
                        tint = if (selected) selectedImg else nonSelectedImg
                    )
                },
                onClick = {
                    navHostController.navigate(item) {
                        popUpTo(navHostController.graph.startDestinationId) {
                            saveState = true
                            inclusive = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                })
        }
    }
}