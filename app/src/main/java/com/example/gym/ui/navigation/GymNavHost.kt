package com.example.gym.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.gym.ui.HomeScreen
import com.example.gym.ui.LauncherScreen
import com.example.gym.ui.SplashScreen
import com.example.gym.ui.WorkoutScreen


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun GymNavHost() {
    val navController = rememberNavController()
    SharedTransitionLayout {
        NavHost(
            navController = navController,
            startDestination = Screen.Launcher.route,
            exitTransition = {
                this.slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.End,
                    tween(1000)
                )
            }
        ) {
            composable(Screen.Launcher.route) {
                LauncherScreen(onLetsGoClicked = {
                    navController.navigate(BottomNavScreen.Home.route)
                })
            }

            composable(BottomNavScreen.Home.route) {
                HomeScreen(
                    animatedVisibilityScope = this,
                    navController = navController,
                    onWorkoutSelected = {
                        navController.navigate(
                            Screen.Workout.route.replace(
                                "{id}",
                                it.id.toString()
                            )
                        )
                    })
            }

            composable(
                Screen.Workout.route,
                arguments = listOf(navArgument("id") { type = NavType.IntType })
            ) {
                it.arguments?.getInt("id")?.let { id ->
                    WorkoutScreen(
                        gymActivityId = id,
                        animatedVisibilityScope = this,
                        onBackClick = {
                            navController.navigateUp()
                        })
                }

            }
            composable(Screen.Splash.route) {
                SplashScreen(navHostController = navController, startApp = {
                    navController.navigate(Screen.Launcher.route)
                })
            }

        }
    }
}