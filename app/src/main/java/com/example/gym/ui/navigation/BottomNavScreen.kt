package com.example.gym.ui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.gym.R

enum class BottomNavScreen(
    val route: String,
    @DrawableRes val icon: Int,
    @StringRes val label: Int
) {
    Home("home", R.drawable.ic_home, R.string.home),
    Favorites("favorites", R.drawable.ic_favourite, R.string.favorites),
    Cart("cart", R.drawable.ic_cart, R.string.cart),
    Profile("profile", R.drawable.ic_profile, R.string.profile),
}

enum class Screen(val route: String) {
    Launcher("launcher"),
    Workout("workout/{id}"),
}