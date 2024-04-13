package com.oys.lolgak

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.oys.lolgak.ui.home.HomeScreen


@Composable
fun NavHostScreen() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Navigation.Routes.HomeScreen
    ) {
        composable(
            route = Navigation.Routes.HomeScreen
        ) {
            HomeScreen()
        }

//        composable(
//            route = Navigation.Routes.REPOS,
//            arguments = listOf(navArgument(name = USER_ID) {
//                type = NavType.StringType
//            })
//        ) { backStackEntry ->
//            val userId = requireNotNull(backStackEntry.arguments?.getString(USER_ID)) { "User id is required as an argument" }
//            ReposScreenDestination(
//                UserId = userId,
//                navController = navController
//            )
//        }
    }
}



fun NavController.navigateToRepos(userId: String) {
    navigate(route = "${Navigation.Routes.USERS}/$userId")
}