package com.example.testapplication.ui.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.testapplication.ui.screen.UserScreen
import com.example.testapplication.ui.screen.UserViewModel
import com.example.testapplication.ui.screen.UsersListScreen

@Composable
fun AppNavigation(
    userViewModel: UserViewModel,
    modifier: Modifier
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "users_list",
        modifier
    ) {
        composable("users_list") {
            UsersListScreen(
                userViewModel,
                navController
            )
        }

        composable(
            route = "user_details/{userId}",
            arguments = listOf(navArgument("userId") { type = NavType.StringType })
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId")
            val users by userViewModel.users.collectAsState()
            val user = users.find { it.uuid == userId }

            if (user != null) {
                UserScreen(
                    user = user,
                    onBackClick = { navController.popBackStack() },
                )
            } else {
                Text("User not found")
            }
        }
    }
}