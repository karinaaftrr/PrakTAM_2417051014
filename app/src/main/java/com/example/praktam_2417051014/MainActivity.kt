package com.example.praktam_2417051014

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.praktam_2417051014.Design.DashboardScreen
import com.example.praktam_2417051014.ui.theme.PrakTAM_2417051014Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PrakTAM_2417051014Theme {
                Scaffold(modifier = Modifier) { innerPadding ->
                    DashboardScreen(innerPadding)
                }
            }
        }
    }
}