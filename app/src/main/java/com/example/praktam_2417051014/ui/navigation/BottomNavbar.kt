package com.example.praktam_2417051014.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Style
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Quiz
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.praktam_2417051014.ui.theme.CardWhite
import com.example.praktam_2417051014.ui.theme.PrimaryColor
import com.example.praktam_2417051014.ui.theme.TextSecondary

@Composable
fun BottomNavBar(
    navController: NavHostController,
    currentRoute: String
) {
    NavigationBar(
        containerColor = CardWhite,
        tonalElevation = 10.dp
    ) {
        NavigationBarItem(
            selected = currentRoute == "dashboard",
            onClick = {
                navController.navigate("dashboard") {
                    launchSingleTop = true
                    popUpTo("dashboard") {
                        inclusive = false
                    }
                }
            },
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Home,
                    contentDescription = null
                )
            },
            label = {
                Text("Beranda")
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = PrimaryColor,
                selectedTextColor = PrimaryColor,
                indicatorColor = PrimaryColor.copy(alpha = 0.18f),
                unselectedIconColor = TextSecondary,
                unselectedTextColor = TextSecondary
            )
        )

        NavigationBarItem(
            selected = currentRoute == "video",
            onClick = {
                navController.navigate("video") {
                    launchSingleTop = true
                }
            },
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Quiz,
                    contentDescription = null
                )
            },
            label = {
                Text("Video")
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = PrimaryColor,
                selectedTextColor = PrimaryColor,
                indicatorColor = PrimaryColor.copy(alpha = 0.18f),
                unselectedIconColor = TextSecondary,
                unselectedTextColor = TextSecondary
            )
        )

        NavigationBarItem(
            selected = currentRoute == "flashcard_session",
            onClick = {
                navController.navigate("flashcard_session") {
                    launchSingleTop = true
                }
            },
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Style,
                    contentDescription = null
                )
            },
            label = {
                Text("Flashcard")
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = PrimaryColor,
                selectedTextColor = PrimaryColor,
                indicatorColor = PrimaryColor.copy(alpha = 0.18f),
                unselectedIconColor = TextSecondary,
                unselectedTextColor = TextSecondary
            )
        )

        NavigationBarItem(
            selected = currentRoute == "profile",
            onClick = {
                navController.navigate("profile") {
                    launchSingleTop = true
                }
            },
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = null
                )
            },
            label = {
                Text("Profil")
            },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = PrimaryColor,
                selectedTextColor = PrimaryColor,
                indicatorColor = PrimaryColor.copy(alpha = 0.18f),
                unselectedIconColor = TextSecondary,
                unselectedTextColor = TextSecondary
            )
        )
    }
}