package com.example.praktam_2417051014.ui.quiz

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.praktam_2417051014.ui.theme.AppBackground
import com.example.praktam_2417051014.ui.theme.BorderSoft
import com.example.praktam_2417051014.ui.theme.CardWhite
import com.example.praktam_2417051014.ui.theme.FavoriteRed
import com.example.praktam_2417051014.ui.theme.PrimaryColor
import com.example.praktam_2417051014.ui.theme.SoftBlueBackground
import com.example.praktam_2417051014.ui.theme.SuccessGreen
import com.example.praktam_2417051014.ui.theme.TextPrimary
import com.example.praktam_2417051014.ui.theme.TextSecondary
import com.example.praktam_2417051014.viewmodel.QuizViewModelIPA

@Composable
fun QuizScreenIPA(
    navController: NavHostController,
    kelas: String,
    mapel: String,
    viewModel: QuizViewModelIPA = viewModel()
) {
    val quizList by viewModel.quizList.collectAsState()
    val currentIndex by viewModel.currentIndex.collectAsState()
    val selectedAnswer by viewModel.selectedAnswer.collectAsState()
    val showPembahasan by viewModel.showPembahasan.collectAsState()
    val isFinished by viewModel.isFinished.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val isError by viewModel.isError.collectAsState()
    val timeLeft by viewModel.timeLeft.collectAsState()

    val timerText = "%02d:%02d".format(
        timeLeft / 60,
        timeLeft % 60
    )

    LaunchedEffect(kelas, mapel) {
        viewModel.loadQuiz(
            kelas = kelas,
            mapel = mapel
        )
    }

    if (isFinished) {
        ResultScreenIPA(
            navController = navController,
            viewModel = viewModel
        )
        return
    }

    val currentQuiz = quizList.getOrNull(currentIndex)

    Scaffold(
        containerColor = AppBackground
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(AppBackground)
                .padding(paddingValues)
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(18.dp))

            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(
                    onClick = {
                        navController.popBackStack()
                    },
                    modifier = Modifier.align(Alignment.CenterStart)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                        contentDescription = "Kembali",
                        tint = TextPrimary
                    )
                }

                Text(
                    text = mapel,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryColor,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            when {
                isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = PrimaryColor)
                    }
                }

                isError || currentQuiz == null -> {
                    Text(
                        text = "Soal belum tersedia.",
                        color = FavoriteRed,
                        fontSize = 14.sp
                    )
                }

                else -> {
                    Text(
                        text = "$kelas IPA",
                        fontSize = 14.sp,
                        color = TextSecondary
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Pertanyaan ${currentIndex + 1} dari ${quizList.size}",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )

                        Card(
                            shape = RoundedCornerShape(50.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = PrimaryColor.copy(alpha = 0.14f)
                            )
                        ) {
                            Text(
                                text = timerText,
                                modifier = Modifier.padding(
                                    horizontal = 14.dp,
                                    vertical = 7.dp
                                ),
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = PrimaryColor
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    LinearProgressIndicator(
                        progress = {
                            (currentIndex + 1).toFloat() / quizList.size.toFloat()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp),
                        color = PrimaryColor,
                        trackColor = BorderSoft
                    )

                    Spacer(modifier = Modifier.height(28.dp))

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(28.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = CardWhite
                        )
                    ) {
                        Text(
                            text = currentQuiz.pertanyaan,
                            modifier = Modifier.padding(22.dp),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary,
                            lineHeight = 28.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    currentQuiz.pilihan.forEachIndexed { index, pilihan ->
                        val isSelected = selectedAnswer == index
                        val isCorrect = index == currentQuiz.jawabanBenar

                        val containerColor = when {
                            showPembahasan && isCorrect ->
                                SuccessGreen.copy(alpha = 0.18f)

                            showPembahasan && isSelected && !isCorrect ->
                                FavoriteRed.copy(alpha = 0.18f)

                            isSelected ->
                                PrimaryColor.copy(alpha = 0.18f)

                            else ->
                                CardWhite
                        }

                        val borderColor = when {
                            showPembahasan && isCorrect ->
                                SuccessGreen

                            showPembahasan && isSelected && !isCorrect ->
                                FavoriteRed

                            isSelected ->
                                PrimaryColor

                            else ->
                                BorderSoft
                        }

                        OutlinedCard(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 12.dp)
                                .clickable {
                                    viewModel.pilihJawaban(index)
                                },
                            shape = RoundedCornerShape(22.dp),
                            colors = CardDefaults.outlinedCardColors(
                                containerColor = containerColor
                            ),
                            border = BorderStroke(
                                width = 1.5.dp,
                                color = borderColor
                            )
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "${('A' + index)}.",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = TextPrimary
                                )

                                Spacer(modifier = Modifier.width(10.dp))

                                Text(
                                    text = pilihan,
                                    fontSize = 15.sp,
                                    color = TextPrimary
                                )
                            }
                        }
                    }

                    if (showPembahasan) {
                        Spacer(modifier = Modifier.height(8.dp))

                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(22.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = SoftBlueBackground
                            )
                        ) {
                            Column(
                                modifier = Modifier.padding(18.dp)
                            ) {
                                Text(
                                    text = if (selectedAnswer == currentQuiz.jawabanBenar) {
                                        "Jawaban Benar"
                                    } else {
                                        "Jawaban Kurang Tepat"
                                    },
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if (selectedAnswer == currentQuiz.jawabanBenar) {
                                        SuccessGreen
                                    } else {
                                        FavoriteRed
                                    }
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                Text(
                                    text = currentQuiz.pembahasan,
                                    fontSize = 14.sp,
                                    color = TextSecondary,
                                    lineHeight = 21.sp
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    Button(
                        onClick = {
                            viewModel.nextQuestion()
                        },
                        enabled = selectedAnswer != null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape = RoundedCornerShape(28.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = PrimaryColor,
                            disabledContainerColor = BorderSoft
                        )
                    ) {
                        Text(
                            text = if (currentIndex == quizList.lastIndex) {
                                "Selesai"
                            } else {
                                "Lanjut"
                            },
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Bold,
                            color = CardWhite
                        )
                    }

                    Spacer(modifier = Modifier.height(28.dp))
                }
            }
        }
    }
}