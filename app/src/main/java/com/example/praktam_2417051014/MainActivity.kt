package com.example.praktam_2417051014

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.praktam_2417051014.ui.navigation.NavigationScreen
import com.example.praktam_2417051014.ui.theme.PraktamTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            PraktamTheme {
                val navController = rememberNavController()

                NavigationScreen(
                    navController = navController
                )
            }
        }
    }
}