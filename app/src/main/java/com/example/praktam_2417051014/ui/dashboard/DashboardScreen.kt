package com.example.praktam_2417051014.ui.dashboard

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.MenuBook
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.School
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.praktam_2417051014.data.firebase.QuizHistoryReader
import com.example.praktam_2417051014.data.model.QuizHistory
import com.example.praktam_2417051014.ui.navigation.BottomNavBar
import com.example.praktam_2417051014.ui.theme.AppBackground
import com.example.praktam_2417051014.ui.theme.BorderSoft
import com.example.praktam_2417051014.ui.theme.CardWhite
import com.example.praktam_2417051014.ui.theme.FavoriteRed
import com.example.praktam_2417051014.ui.theme.PrimaryColor
import com.example.praktam_2417051014.ui.theme.SecondaryColor
import com.example.praktam_2417051014.ui.theme.TextPrimary
import com.example.praktam_2417051014.ui.theme.TextSecondary
import com.example.praktam_2417051014.viewmodel.DashboardViewModel
import com.google.firebase.auth.FirebaseAuth
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun DashboardScreen(
    navController: NavHostController,
    viewModel: DashboardViewModel = viewModel()
) {
    val jenjang by viewModel.jenjang.collectAsState()
    val kategori by viewModel.selectedKategori.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val isError by viewModel.isError.collectAsState()

    var riwayatKuis by remember {
        mutableStateOf<List<QuizHistory>>(emptyList())
    }

    var isHistoryLoading by remember {
        mutableStateOf(true)
    }

    val quizHistoryReader = remember {
        QuizHistoryReader()
    }

    LaunchedEffect(Unit) {
        isHistoryLoading = true
        riwayatKuis = quizHistoryReader.getQuizHistory()
        isHistoryLoading = false
    }

    val username =
        FirebaseAuth.getInstance()
            .currentUser
            ?.displayName
            ?: "Pengguna"

    val filteredJenjang =
        jenjang.filter {
            it.kategori.equals(
                kategori,
                ignoreCase = true
            )
        }

    val riwayatDashboard = riwayatKuis.take(4)

    Scaffold(
        containerColor = AppBackground,
        bottomBar = {
            BottomNavBar(
                navController = navController,
                currentRoute = "dashboard"
            )
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(AppBackground)
                .padding(paddingValues)
                .padding(horizontal = 22.dp),
            contentPadding = PaddingValues(
                bottom = 26.dp
            )
        ) {
            item {
                Spacer(
                    modifier = Modifier.height(42.dp)
                )

                Text(
                    text = "Halo $username",
                    fontSize = 31.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )

                Spacer(
                    modifier = Modifier.height(6.dp)
                )

                Text(
                    text = "Siap untuk petualangan belajar hari ini?",
                    fontSize = 15.sp,
                    color = TextSecondary
                )

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                Row {
                    Button(
                        onClick = {
                            viewModel.pilihKategori("IPA")
                        },
                        modifier = Modifier
                            .width(86.dp)
                            .height(44.dp),
                        shape = RoundedCornerShape(50.dp),
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor =
                                if (kategori == "IPA") {
                                    PrimaryColor
                                } else {
                                    BorderSoft
                                }
                        )
                    ) {
                        Text(
                            text = "IPA",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color =
                                if (kategori == "IPA") {
                                    CardWhite
                                } else {
                                    TextSecondary
                                }
                        )
                    }

                    Spacer(
                        modifier = Modifier.width(14.dp)
                    )

                    Button(
                        onClick = {
                            viewModel.pilihKategori("IPS")
                        },
                        modifier = Modifier
                            .width(86.dp)
                            .height(44.dp),
                        shape = RoundedCornerShape(50.dp),
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor =
                                if (kategori == "IPS") {
                                    PrimaryColor
                                } else {
                                    BorderSoft
                                }
                        )
                    ) {
                        Text(
                            text = "IPS",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color =
                                if (kategori == "IPS") {
                                    CardWhite
                                } else {
                                    TextSecondary
                                }
                        )
                    }
                }

                Spacer(
                    modifier = Modifier.height(38.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Pilih Jenjang",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )

                    Spacer(
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(
                    modifier = Modifier.height(16.dp)
                )
            }

            if (isLoading) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                vertical = 20.dp
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            color = PrimaryColor
                        )
                    }
                }
            }

            if (isError) {
                item {
                    Text(
                        text = "Data belum berhasil dimuat.",
                        fontSize = 14.sp,
                        color = FavoriteRed,
                        modifier = Modifier.padding(
                            bottom = 16.dp
                        )
                    )
                }
            }

            itemsIndexed(
                filteredJenjang
            ) { index, item ->

                val backgroundColor =
                    when (index) {
                        0 -> Color(0xFFDFF2FF)
                        1 -> Color(0xFFF1E3FF)
                        else -> Color(0xFFFFF3C7)
                    }

                val textColor =
                    when (index) {
                        0 -> PrimaryColor
                        1 -> SecondaryColor
                        else -> Color(0xFFC96A00)
                    }

                val icon =
                    if (index == 2) {
                        Icons.Outlined.School
                    } else {
                        Icons.AutoMirrored.Outlined.MenuBook
                    }

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(108.dp)
                        .padding(bottom = 14.dp)
                        .clickable {
                            if (
                                item.kategori.equals(
                                    "IPA",
                                    ignoreCase = true
                                )
                            ) {
                                navController.navigate(
                                    "detail_mapel_ipa/${item.kelas}"
                                )
                            } else {
                                navController.navigate(
                                    "detail_mapel_ips/${item.kelas}"
                                )
                            }
                        },
                    shape = RoundedCornerShape(28.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = backgroundColor
                    ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 0.dp
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(
                                horizontal = 24.dp
                            ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier.weight(1f)
                        ) {
                            Text(
                                text = item.kelas,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = textColor
                            )

                            Spacer(
                                modifier = Modifier.height(8.dp)
                            )

                            Text(
                                text = item.deskripsi,
                                fontSize = 14.sp,
                                color = textColor
                            )
                        }

                        Box(
                            modifier = Modifier
                                .size(50.dp)
                                .clip(CircleShape)
                                .background(
                                    CardWhite.copy(
                                        alpha = 0.85f
                                    )
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = icon,
                                contentDescription = null,
                                tint = textColor
                            )
                        }
                    }
                }
            }

            item {
                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Riwayat Kuis",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )

                    Spacer(
                        modifier = Modifier.weight(1f)
                    )

                    Text(
                        text = "Lihat Selengkapnya",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = PrimaryColor,
                        modifier = Modifier.clickable {
                            navController.navigate("riwayat_kuis")
                        }
                    )
                }

                Spacer(
                    modifier = Modifier.height(6.dp)
                )

                Text(
                    text = "Hasil kuis terbaru yang sudah kamu kerjakan.",
                    fontSize = 14.sp,
                    color = TextSecondary
                )

                Spacer(
                    modifier = Modifier.height(16.dp)
                )
            }

            if (isHistoryLoading) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 18.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            color = PrimaryColor
                        )
                    }
                }
            }

            if (!isHistoryLoading && riwayatKuis.isEmpty()) {
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(24.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = CardWhite
                        ),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 1.dp
                        )
                    ) {
                        Text(
                            text = "Belum ada riwayat kuis.",
                            fontSize = 14.sp,
                            color = TextSecondary,
                            modifier = Modifier.padding(18.dp)
                        )
                    }
                }
            }

            itemsIndexed(
                riwayatDashboard
            ) { index, item ->
                RiwayatKuisCard(
                    index = index,
                    item = item
                )
            }
        }
    }
}

@Composable
fun RiwayatKuisCard(
    index: Int,
    item: QuizHistory
) {
    val nilaiColor =
        when {
            item.skor >= 90 -> SecondaryColor
            item.skor >= 80 -> PrimaryColor
            else -> FavoriteRed
        }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 14.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = CardWhite
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(
                        when (index) {
                            0 -> Color(0xFFDFF2FF)
                            1 -> Color(0xFFF1E3FF)
                            else -> Color(0xFFFFF3C7)
                        }
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.History,
                    contentDescription = null,
                    tint = nilaiColor
                )
            }

            Spacer(
                modifier = Modifier.width(14.dp)
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = item.mapel,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                Text(
                    text = "${item.kelas} • ${item.kategori}",
                    fontSize = 13.sp,
                    color = TextSecondary
                )

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                Text(
                    text = "Benar ${item.benar} dari ${item.totalSoal} soal",
                    fontSize = 12.sp,
                    color = TextSecondary
                )

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                Text(
                    text = formatTanggal(item.createdAt),
                    fontSize = 12.sp,
                    color = TextSecondary
                )
            }

            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = item.skor.toString(),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = nilaiColor
                )

                Text(
                    text = "Nilai",
                    fontSize = 12.sp,
                    color = TextSecondary
                )
            }
        }
    }
}

fun formatTanggal(createdAt: Long): String {
    return if (createdAt == 0L) {
        "-"
    } else {
        val localeIndonesia = Locale.forLanguageTag("id-ID")
        val formatter = SimpleDateFormat("dd MMM yyyy", localeIndonesia)
        formatter.format(Date(createdAt))
    }
}