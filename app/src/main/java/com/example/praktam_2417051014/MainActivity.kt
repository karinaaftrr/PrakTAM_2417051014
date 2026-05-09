package com.example.praktam_2417051014

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.praktam_2417051014.Navigation.NavigationScreen
import com.example.praktam_2417051014.ui.theme.PrakTAM_2417051014Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            PrakTAM_2417051014Theme {
                val navController = rememberNavController()
                NavigationScreen(navController)
            }
        }
    }
}