package com.fetchrewards.presentation.navigation

// It contains route names to all three screens
sealed class Routes(val route: String) {
    data object Home : Routes("home")
}