package com.example.gym.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.gym.ui.HomeScreen
import com.example.gym.ui.LauncherScreen
import com.example.gym.ui.WorkoutScreen


@Composable
fun GymNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Launcher.route) {
        composable(Screen.Launcher.route) {
            LauncherScreen(onLetsGoClicked = {
                navController.navigate(BottomNavScreen.Home.route)
            })
        }

        composable(BottomNavScreen.Home.route) {
            HomeScreen(navController = navController)
        }

        composable(
            Screen.Workout.route,
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) {
            navController.currentBackStackEntry?.arguments?.getInt("id")?.let { id ->
                WorkoutScreen(gymActivityId = id)
            }

        }

    }
}