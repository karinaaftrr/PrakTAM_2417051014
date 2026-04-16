package com.example.praktam_2417051014

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.praktam_2417051014.Design.DashboardScreen
import com.example.praktam_2417051014.Design.PilihMapelScreen
import com.example.praktam_2417051014.ui.theme.PrakTAM_2417051014Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PrakTAM_2417051014Theme {
                var currentScreen by remember { mutableStateOf("dashboard") }
                var selectedKelas by remember { mutableStateOf("") }

                Scaffold(modifier = Modifier) { innerPadding ->
                    if (currentScreen == "dashboard") {
                        DashboardScreen(
                            innerPadding = innerPadding,
                            onNavigate = { kelas ->
                                selectedKelas = kelas
                                currentScreen = "pilih_mapel"
                            }
                        )
                    } else {
                        PilihMapelScreen(
                            namaKelas = selectedKelas,
                            innerPadding = innerPadding
                        )
                    }
                }
            }
        }
    }
}