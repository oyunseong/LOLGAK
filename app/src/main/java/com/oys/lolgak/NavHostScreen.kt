package com.oys.lolgak

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.oys.lolgak.ui.detail.DetailUserScreen
import com.oys.lolgak.ui.home.HomeScreen
import com.oys.lolgak.ui.model.Account


@Composable
fun NavHostScreen() {
    val navController = rememberNavController()
    val gson = Gson()

    fun clearAndNavigate(route: String) {
        navController.navigate(route) {
            launchSingleTop = true
            popUpTo(0) { inclusive = true }
        }
    }

    fun popUp() {
        navController.popBackStack()
    }

    fun navigate(route: String) {
        navController.navigate(route) { launchSingleTop = true }
    }

    NavHost(
        navController = navController,
        startDestination = Navigation.Routes.HomeScreen
    ) {
        composable(
            route = Navigation.Routes.HomeScreen
        ) {
            HomeScreen(
                navigate = ::navigate,

            )
        }

        composable(
            route = "${Navigation.Routes.DetailUserScreen}/{accountJson}",
            arguments = listOf(navArgument("accountJson") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            val accountJson = backStackEntry.arguments?.getString("accountJson")
            val account = gson.fromJson(accountJson, Account::class.java)
            DetailUserScreen(account = account)
        }
    }


}

