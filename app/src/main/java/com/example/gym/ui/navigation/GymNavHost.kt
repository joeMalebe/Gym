package com.example.gym.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gym.ui.HomeScreen
import com.example.gym.ui.LauncherScreen


@Composable
fun GymNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Launcher.route) {
        composable(Screen.Launcher.route) {
            LauncherScreen(onLetsGoClicked = {
                navController.navigate(Screen.Home.route)
            })
        }

        composable(Screen.Home.route) {
            HomeScreen(navController = navController)
        }

    }
}