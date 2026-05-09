package com.example.praktam_2417051014.Navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.NavType
import com.example.praktam_2417051014.Design.*
import com.example.praktam_2417051014.LoginRegist.LoginScreen

@Composable
fun NavigationScreen(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = "onboarding"
    ) {

        composable("onboarding") {
            OnBoardingScreen(navController)
        }

        composable("login") {
            LoginScreen(navController)
        }

        composable("dashboard") {
            DashboardScreen(
                innerPadding = PaddingValues(0.dp),
                onNavigate = { route ->
                    navController.navigate(route)
                }
            )
        }

        composable(
            route = "pilih_mapel/{namaKelas}",
            arguments = listOf(
                navArgument("namaKelas") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->

            val namaKelas = backStackEntry.arguments?.getString("namaKelas") ?: ""

            PilihMapelScreen(
                navController = navController,
                namaKelas = namaKelas,
                innerPadding = PaddingValues(0.dp)
            )
        }
    }
}