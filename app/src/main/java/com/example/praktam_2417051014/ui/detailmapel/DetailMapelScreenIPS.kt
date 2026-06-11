package com.example.praktam_2417051014.ui.detailmapel

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.praktam_2417051014.R
import com.example.praktam_2417051014.data.firebase.QuizHistoryReader
import com.example.praktam_2417051014.data.model.Mapel
import com.example.praktam_2417051014.ui.theme.AppBackground
import com.example.praktam_2417051014.ui.theme.CardWhite
import com.example.praktam_2417051014.ui.theme.FavoriteRed
import com.example.praktam_2417051014.ui.theme.PrimaryColor
import com.example.praktam_2417051014.ui.theme.SecondaryColor
import com.example.praktam_2417051014.ui.theme.SoftBlueBackground
import com.example.praktam_2417051014.ui.theme.SuccessGreen
import com.example.praktam_2417051014.ui.theme.TextPrimary
import com.example.praktam_2417051014.ui.theme.TextSecondary
import com.example.praktam_2417051014.viewmodel.MapelViewModel
import kotlinx.coroutines.launch

@Composable
fun DetailMapelScreenIPS(
    navController: NavHostController,
    kelas: String,
    viewModel: MapelViewModel = viewModel()
) {
    val mapel by viewModel.mapel.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val isError by viewModel.isError.collectAsState()

    val listMapel = mapel.filter {
        it.kelas.equals(kelas, ignoreCase = true) &&
                it.kategori.equals("IPS", ignoreCase = true)
    }

    val scope = rememberCoroutineScope()
    val reader = remember { QuizHistoryReader() }
    val scoreMap = remember { mutableStateMapOf<String, Int>() }
    val lifecycleOwner = LocalLifecycleOwner.current

    fun loadScores() {
        scope.launch {
            listMapel.forEach { item ->
                val score = reader.getLatestScore(
                    kelas = item.kelas,
                    kategori = item.kategori,
                    mapel = item.nama
                )

                if (score != null) {
                    scoreMap[item.nama] = score
                }
            }
        }
    }

    LaunchedEffect(listMapel) {
        loadScores()
    }

    DisposableEffect(lifecycleOwner, listMapel) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                loadScores()
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

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

            DetailTopBarIPS(navController = navController)

            Spacer(modifier = Modifier.height(38.dp))

            Text(
                text = "$kelas IPS",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Pilih mata pelajaran untuk mulai belajar",
                fontSize = 14.sp,
                color = TextSecondary
            )

            Spacer(modifier = Modifier.height(26.dp))

            when {
                isLoading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 70.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = PrimaryColor)
                    }
                }

                isError || listMapel.isEmpty() -> {
                    Text(
                        text = "Data mata pelajaran belum tersedia.",
                        color = FavoriteRed,
                        fontSize = 14.sp
                    )
                }

                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        contentPadding = PaddingValues(bottom = 24.dp)
                    ) {
                        itemsIndexed(listMapel) { index, item ->
                            MapelCardIPS(
                                item = item,
                                index = index,
                                navController = navController,
                                nilai = scoreMap[item.nama]
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun MapelCardIPS(
    item: Mapel,
    index: Int,
    navController: NavHostController,
    nilai: Int?
) {
    val containerColor = when (index) {
        0 -> SoftBlueBackground
        1 -> SuccessGreen.copy(alpha = 0.14f)
        2 -> SecondaryColor.copy(alpha = 0.14f)
        else -> Color(0xFFFFF3E6)
    }

    val accentColor = when (index) {
        0 -> PrimaryColor
        1 -> SuccessGreen
        2 -> SecondaryColor
        else -> Color(0xFFFF7A00)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(172.dp)
            .clickable {
                navController.navigate(
                    "quiz_ips/${item.kelas}/${item.nama}"
                )
            },
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(
            containerColor = containerColor
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(72.dp)
                    .clip(RoundedCornerShape(22.dp))
                    .background(CardWhite),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(
                        id = getMapelImageIPS(item.gambar)
                    ),
                    contentDescription = item.nama,
                    modifier = Modifier.size(42.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = item.nama,
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(7.dp))

                Text(
                    text = item.deskripsi,
                    fontSize = 13.sp,
                    color = TextSecondary,
                    lineHeight = 18.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(10.dp))

                if (nilai == null) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(50.dp))
                            .background(accentColor.copy(alpha = 0.14f))
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = item.jumlahQuiz,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = accentColor
                        )
                    }
                } else {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(50.dp))
                                .background(accentColor.copy(alpha = 0.14f))
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                        ) {
                            Text(
                                text = "Nilai: $nilai",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = accentColor
                            )
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = "Coba Lagi",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryColor
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun DetailTopBarIPS(
    navController: NavHostController
) {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                contentDescription = "Kembali",
                tint = TextPrimary
            )
        }

        Text(
            text = "BelajarYuk",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = PrimaryColor,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

private fun getMapelImageIPS(
    gambar: String
): Int {
    return when (gambar.lowercase()) {
        "sosiologi" -> R.drawable.sosiologi
        "ekonomi" -> R.drawable.ekonomi
        "geografi" -> R.drawable.geografi
        "sejarah" -> R.drawable.sejarah
        else -> R.drawable.logo_basickuiz
    }
}