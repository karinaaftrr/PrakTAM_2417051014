package com.example.praktam_2417051014.ui.flashcard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lightbulb
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material.icons.outlined.TouchApp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.praktam_2417051014.data.model.FlashCard
import com.example.praktam_2417051014.data.repository.FlashcardRepository
import com.example.praktam_2417051014.ui.navigation.BottomNavBar
import com.example.praktam_2417051014.ui.theme.AppBackground
import com.example.praktam_2417051014.ui.theme.CardWhite
import com.example.praktam_2417051014.ui.theme.FavoriteRed
import com.example.praktam_2417051014.ui.theme.PrimaryColor
import com.example.praktam_2417051014.ui.theme.SecondaryColor
import com.example.praktam_2417051014.ui.theme.TextPrimary
import com.example.praktam_2417051014.ui.theme.TextSecondary

@Composable
fun FlashcardSessionScreen(
    navController: NavHostController
) {
    val repository = remember {
        FlashcardRepository()
    }

    var flashcards by remember {
        mutableStateOf<List<FlashCard>>(emptyList())
    }

    var currentIndex by remember {
        mutableIntStateOf(0)
    }

    var isFlipped by remember {
        mutableStateOf(false)
    }

    var isLoading by remember {
        mutableStateOf(true)
    }

    var isError by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        try {
            isLoading = true
            isError = false
            flashcards = repository.getFlashcardSession()
            currentIndex = 0
            isFlipped = false
        } catch (e: Exception) {
            isError = true
        } finally {
            isLoading = false
        }
    }

    Scaffold(
        containerColor = AppBackground,
        bottomBar = {
            BottomNavBar(
                navController = navController,
                currentRoute = "flashcard_session"
            )
        }
    ) { paddingValues ->

        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = PrimaryColor
                )
            }
        } else if (isError) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(22.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Data flashcard belum berhasil dimuat.",
                    fontSize = 16.sp,
                    color = FavoriteRed,
                    textAlign = TextAlign.Center
                )
            }
        } else if (flashcards.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(22.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Belum ada data flashcard.",
                    fontSize = 16.sp,
                    color = TextSecondary,
                    textAlign = TextAlign.Center
                )
            }
        } else {
            val currentCard = flashcards[currentIndex]
            val progress =
                (currentIndex + 1).toFloat() / flashcards.size.toFloat()

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(AppBackground)
                    .padding(paddingValues)
                    .padding(horizontal = 22.dp),
                contentPadding = PaddingValues(
                    bottom = 24.dp
                )
            ) {
                item {
                    Spacer(
                        modifier = Modifier.height(28.dp)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.Top
                    ) {
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = "FlashCard",
                                fontSize = 31.sp,
                                fontWeight = FontWeight.Bold,
                                color = TextPrimary
                            )

                            Spacer(
                                modifier = Modifier.height(6.dp)
                            )

                            Text(
                                text = "Mari Mengingat Seputar Pengetahuan",
                                fontSize = 15.sp,
                                color = TextSecondary
                            )
                        }

                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(50.dp))
                                .background(
                                    if (currentCard.kategori.equals("IPA", ignoreCase = true)) {
                                        Color(0xFFD8FFF0)
                                    } else {
                                        Color(0xFFF1E3FF)
                                    }
                                )
                                .padding(
                                    horizontal = 16.dp,
                                    vertical = 8.dp
                                )
                        ) {
                            Text(
                                text = currentCard.kategori,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color =
                                    if (currentCard.kategori.equals("IPA", ignoreCase = true)) {
                                        Color(0xFF008B5A)
                                    } else {
                                        SecondaryColor
                                    }
                            )
                        }
                    }

                    Spacer(
                        modifier = Modifier.height(30.dp)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Sesi Belajar",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = TextSecondary
                        )

                        Spacer(
                            modifier = Modifier.weight(1f)
                        )

                        Text(
                            text = "${currentIndex + 1}",
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryColor
                        )

                        Text(
                            text = "/${flashcards.size}",
                            fontSize = 30.sp,
                            color = TextSecondary
                        )
                    }

                    Spacer(
                        modifier = Modifier.height(14.dp)
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(10.dp)
                            .clip(RoundedCornerShape(50.dp))
                            .background(Color(0xFFE6E6E6))
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(
                                    progress.coerceIn(
                                        0f,
                                        1f
                                    )
                                )
                                .height(10.dp)
                                .clip(RoundedCornerShape(50.dp))
                                .background(PrimaryColor)
                        )
                    }

                    Spacer(
                        modifier = Modifier.height(42.dp)
                    )

                    FlashcardItem(
                        flashcard = currentCard,
                        isFlipped = isFlipped,
                        onClick = {
                            isFlipped = !isFlipped
                        }
                    )

                    Spacer(
                        modifier = Modifier.height(28.dp)
                    )

                    Text(
                        text =
                            if (isFlipped) {
                                "Bagian belakang kartu menampilkan jawaban dan pembahasan."
                            } else {
                                "Tekan kartu untuk melihat jawaban dan pembahasan."
                            },
                        fontSize = 14.sp,
                        color = TextSecondary,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(
                        modifier = Modifier.height(30.dp)
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .navigationBarsPadding(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        OutlinedButton(
                            onClick = {
                                if (currentIndex > 0) {
                                    currentIndex--
                                    isFlipped = false
                                }
                            },
                            enabled = currentIndex > 0,
                            modifier = Modifier
                                .weight(1f)
                                .height(54.dp),
                            shape = RoundedCornerShape(50.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Transparent,
                                contentColor = PrimaryColor,
                                disabledContainerColor = Color.Transparent,
                                disabledContentColor = TextSecondary
                            )
                        ) {
                            Text(
                                text = "Sebelumnya",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Spacer(
                            modifier = Modifier.width(12.dp)
                        )

                        Button(
                            onClick = {
                                if (currentIndex < flashcards.lastIndex) {
                                    currentIndex++
                                    isFlipped = false
                                } else {
                                    flashcards = flashcards.shuffled()
                                    currentIndex = 0
                                    isFlipped = false
                                }
                            },
                            modifier = Modifier
                                .weight(1f)
                                .height(54.dp),
                            shape = RoundedCornerShape(50.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = PrimaryColor
                            )
                        ) {
                            Text(
                                text =
                                    if (currentIndex < flashcards.lastIndex) {
                                        "Berikutnya"
                                    } else {
                                        "Ulangi"
                                    },
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = CardWhite
                            )
                        }
                    }

                    Spacer(
                        modifier = Modifier.height(14.dp)
                    )

                    OutlinedButton(
                        onClick = {
                            flashcards = flashcards.shuffled()
                            currentIndex = 0
                            isFlipped = false
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(54.dp),
                        shape = RoundedCornerShape(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            contentColor = SecondaryColor
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Refresh,
                            contentDescription = null,
                            tint = SecondaryColor
                        )

                        Spacer(
                            modifier = Modifier.width(8.dp)
                        )

                        Text(
                            text = "Acak Kartu Lagi",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun FlashcardItem(
    flashcard: FlashCard,
    isFlipped: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(420.dp)
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(36.dp),
        colors = CardDefaults.cardColors(
            containerColor = CardWhite
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(28.dp),
            contentAlignment = Alignment.Center
        ) {
            if (!isFlipped) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(62.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFF1F1F1)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Lightbulb,
                            contentDescription = null,
                            tint = TextSecondary,
                            modifier = Modifier.size(34.dp)
                        )
                    }

                    Spacer(
                        modifier = Modifier.height(26.dp)
                    )

                    Text(
                        text = flashcard.pertanyaan,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryColor,
                        textAlign = TextAlign.Center,
                        lineHeight = 36.sp
                    )

                    Spacer(
                        modifier = Modifier.height(34.dp)
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.TouchApp,
                            contentDescription = null,
                            tint = TextSecondary,
                            modifier = Modifier.size(20.dp)
                        )

                        Spacer(
                            modifier = Modifier.width(8.dp)
                        )

                        Text(
                            text = "TEKAN UNTUK MEMBALIK",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = TextSecondary,
                            letterSpacing = 1.5.sp
                        )
                    }

                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )

                    Text(
                        text = "${flashcard.kelas} • ${flashcard.mapel}",
                        fontSize = 13.sp,
                        color = TextSecondary
                    )
                }
            } else {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Jawaban",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextSecondary
                    )

                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )

                    Text(
                        text = flashcard.jawaban,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryColor,
                        textAlign = TextAlign.Center,
                        lineHeight = 33.sp
                    )

                    Spacer(
                        modifier = Modifier.height(28.dp)
                    )

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(22.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFF4F6FF)
                        ),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 0.dp
                        )
                    ) {
                        Column(
                            modifier = Modifier.padding(18.dp)
                        ) {
                            Text(
                                text = "Pembahasan",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = TextPrimary
                            )

                            Spacer(
                                modifier = Modifier.height(8.dp)
                            )

                            Text(
                                text = flashcard.pembahasan,
                                fontSize = 14.sp,
                                color = TextSecondary,
                                lineHeight = 21.sp
                            )
                        }
                    }

                    Spacer(
                        modifier = Modifier.height(18.dp)
                    )

                    Text(
                        text = "Tekan kartu untuk kembali ke pertanyaan.",
                        fontSize = 13.sp,
                        color = TextSecondary,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}
