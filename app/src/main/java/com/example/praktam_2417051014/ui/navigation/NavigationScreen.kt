package com.example.praktam_2417051014.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.praktam_2417051014.ui.dashboard.DashboardScreen
import com.example.praktam_2417051014.ui.dashboard.RiwayaQuizScreen
import com.example.praktam_2417051014.ui.detailmapel.DetailMapelScreenIPA
import com.example.praktam_2417051014.ui.detailmapel.DetailMapelScreenIPS
import com.example.praktam_2417051014.ui.flashcard.FlashcardSessionScreen
import com.example.praktam_2417051014.ui.loginregist.ForgotPasswordScreen
import com.example.praktam_2417051014.ui.loginregist.LoginScreen
import com.example.praktam_2417051014.ui.loginregist.RegisterScreen
import com.example.praktam_2417051014.ui.onboarding.OnboardingScreen
import com.example.praktam_2417051014.ui.profile.ProfileScreen
import com.example.praktam_2417051014.ui.quiz.QuizScreenIPA
import com.example.praktam_2417051014.ui.quiz.QuizScreenIPS
import com.example.praktam_2417051014.ui.video.VideoScreen
import com.google.firebase.auth.FirebaseAuth

@Composable
fun NavigationScreen(
    navController: NavHostController
) {

    NavHost(
        navController = navController,
        startDestination = "session_check"
    ) {

        composable(
            route = "session_check"
        ) {

            LaunchedEffect(Unit) {

                val currentUser = FirebaseAuth
                    .getInstance()
                    .currentUser

                if (currentUser != null) {

                    navController.navigate(
                        "dashboard"
                    ) {
                        popUpTo(
                            "session_check"
                        ) {
                            inclusive = true
                        }
                    }

                } else {

                    navController.navigate(
                        "onboarding"
                    ) {
                        popUpTo(
                            "session_check"
                        ) {
                            inclusive = true
                        }
                    }

                }

            }

        }

        composable(
            route = "onboarding"
        ) {

            OnboardingScreen(
                onFinish = {
                    navController.navigate(
                        "login"
                    ) {
                        popUpTo(
                            "onboarding"
                        ) {
                            inclusive = true
                        }
                    }
                }
            )

        }

        composable(
            route = "login"
        ) {

            LoginScreen(
                navController = navController
            )

        }

        composable(
            route = "forgot_password"
        ) {

            ForgotPasswordScreen(
                navController = navController
            )

        }

        composable(
            route = "register"
        ) {

            RegisterScreen(
                navController = navController
            )

        }

        composable(
            route = "dashboard"
        ) {

            DashboardScreen(
                navController = navController
            )

        }

        composable(
            route = "video"
        ) {

            VideoScreen(
                navController = navController
            )

        }

        composable(
            route = "flashcard_session"
        ) {

            FlashcardSessionScreen(
                navController = navController
            )

        }

        composable(
            route = "riwayat_kuis"
        ) {

            RiwayaQuizScreen(
                navController = navController
            )

        }

        composable(
            route = "detail_mapel_ipa/{kelas}"
        ) { backStackEntry ->

            val kelas =
                backStackEntry
                    .arguments
                    ?.getString(
                        "kelas"
                    )
                    ?: "Kelas 10"

            DetailMapelScreenIPA(
                navController = navController,
                kelas = kelas
            )

        }

        composable(
            route = "detail_mapel_ips/{kelas}"
        ) { backStackEntry ->

            val kelas =
                backStackEntry
                    .arguments
                    ?.getString(
                        "kelas"
                    )
                    ?: "Kelas 10"

            DetailMapelScreenIPS(
                navController = navController,
                kelas = kelas
            )

        }

        composable(
            route = "quiz_ipa/{kelas}/{mapel}"
        ) { backStackEntry ->

            val kelas =
                backStackEntry
                    .arguments
                    ?.getString(
                        "kelas"
                    )
                    ?: "Kelas 10"

            val mapel =
                backStackEntry
                    .arguments
                    ?.getString(
                        "mapel"
                    )
                    ?: "Matematika"

            QuizScreenIPA(
                navController = navController,
                kelas = kelas,
                mapel = mapel
            )

        }

        composable(
            route = "quiz_ips/{kelas}/{mapel}"
        ) { backStackEntry ->

            val kelas =
                backStackEntry
                    .arguments
                    ?.getString(
                        "kelas"
                    )
                    ?: "Kelas 10"

            val mapel =
                backStackEntry
                    .arguments
                    ?.getString(
                        "mapel"
                    )
                    ?: "Sosiologi"

            QuizScreenIPS(
                navController = navController,
                kelas = kelas,
                mapel = mapel
            )

        }

        composable(
            route = "progress"
        ) {

            FlashcardSessionScreen(
                navController = navController
            )

        }

        composable(
            route = "profile"
        ) {

            ProfileScreen(
                navController = navController
            )

        }

    }

}