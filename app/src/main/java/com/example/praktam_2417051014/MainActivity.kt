package com.example.praktam_2417051014

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.praktam_2417051014.Design.DashboardScreen
import com.example.praktam_2417051014.Design.PilihMapelScreen
import com.example.praktam_2417051014.ui.theme.PrakTAM_2417051014Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PrakTAM_2417051014Theme {
                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "dashboard"
                    ) {
                        composable("dashboard") {
                            DashboardScreen(
                                innerPadding = innerPadding,
                                onNavigate = { kelas ->
                                    navController.navigate("pilih_mapel/$kelas")
                                }
                            )
                        }
                        composable(
                            route = "pilih_mapel/{namaKelas}",
                            arguments = listOf(navArgument("namaKelas") { type = NavType.StringType })
                        ) { backStackEntry ->
                            val namaKelas = backStackEntry.arguments?.getString("namaKelas") ?: ""
                            PilihMapelScreen(
                                navController = navController,
                                namaKelas = namaKelas,
                                innerPadding = innerPadding
                            )
                        }
                    }
                }
            }
        }
    }
}