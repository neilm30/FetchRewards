package com.fetchrewards.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.fetchrewards.presentation.composeui.HomeScreen
import com.fetchrewards.presentation.viewmodel.FetchRewardsViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.Home.route,
    ) {
        setHomeScreenDestination(navController)
    }
}

private fun NavGraphBuilder.setHomeScreenDestination(navController: NavHostController) {
    composable(Routes.Home.route) {
        val viewModel: FetchRewardsViewModel = getViewModel()
        HomeScreen(
            navController,
            state = viewModel.dataState.collectAsState().value,
            onEvent = viewModel::onEventChange,
        )
    }
}
