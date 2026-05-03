package com.example.praktam_2417051014.Design

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.praktam_2417051014.R
import com.example.praktam_2417051014.model.MataPelajaran
import com.example.praktam_2417051014.network.RetrofitClient
import com.example.praktam_2417051014.ui.theme.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun DashboardScreen(innerPadding: PaddingValues, onNavigate: (String) -> Unit) {

    var listMapel by remember { mutableStateOf<List<MataPelajaran>>(emptyList()) }
    var isLoadingData by remember { mutableStateOf(true) }

    var isLoading by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        try {
            listMapel = RetrofitClient.instance.getMapels()
            isLoadingData = false
        } catch (e: Exception) {
            isLoadingData = false
        }
    }

    val kelasList = listOf(
        Pair("Kelas 10", R.drawable.kelas10),
        Pair("Kelas 11", R.drawable.kelas11),
        Pair("Kelas 12", R.drawable.kelas12)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppBackground)
            .padding(innerPadding)
    ) {

        if (isLoadingData) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = BluePrimary)
            }
        } else {

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(bottom = 24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .clip(RoundedCornerShape(24.dp))
                            .background(
                                Brush.horizontalGradient(
                                    listOf(BluePrimary, BlueSecondary)
                                )
                            )
                            .padding(20.dp)
                    ) {
                        Column {
                            Text(
                                "BasicKuizz",
                                color = TextPrimary,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                "Mata Pelajaran Kurikulum Merdeka",
                                color = TextLight
                            )
                        }
                    }
                }

                item {
                    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                        Text(
                            "Hi, Karina Fitriamalia",
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )
                        Text(
                            "Mari mengerjakan soal!!",
                            color = TextSecondary
                        )
                    }
                }

                item {
                    Text(
                        "Pilih Kelas",
                        modifier = Modifier.padding(start = 16.dp),
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )

                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {

                        items(kelasList) { kelas ->

                            Card(
                                modifier = Modifier
                                    .width(200.dp)
                                    .height(190.dp),
                                shape = RoundedCornerShape(20.dp),
                                colors = CardDefaults.cardColors(containerColor = CardWhite)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(12.dp),
                                    verticalArrangement = Arrangement.SpaceBetween
                                ) {

                                    Image(
                                        painter = painterResource(kelas.second),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(90.dp),
                                        contentScale = ContentScale.Crop
                                    )

                                    Text(
                                        kelas.first,
                                        fontWeight = FontWeight.Bold,
                                        color = TextPrimary
                                    )

                                    Button(
                                        onClick = {
                                            coroutineScope.launch {
                                                isLoading = true
                                                delay(2000)
                                                isLoading = false
                                                snackbarHostState.showSnackbar("Masuk ke ${kelas.first}")
                                                onNavigate(kelas.first)
                                            }
                                        },
                                        modifier = Modifier.fillMaxWidth(),
                                        enabled = !isLoading,
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = BluePrimary,
                                            contentColor = TextPrimary
                                        )
                                    ) {
                                        if (isLoading) {
                                            CircularProgressIndicator(
                                                modifier = Modifier.size(18.dp),
                                                color = TextPrimary
                                            )
                                        } else {
                                            Text("Mulai")
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                item {
                    Text(
                        "History",
                        modifier = Modifier.padding(start = 16.dp),
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                }

                items(listMapel.take(3)) { mapel ->

                    var isFavorite by remember { mutableStateOf(false) }

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        shape = RoundedCornerShape(18.dp),
                        colors = CardDefaults.cardColors(containerColor = BlueDark)
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            AsyncImage(
                                model = mapel.imageUrl,
                                contentDescription = null,
                                placeholder = painterResource(R.drawable.matematika),
                                error = painterResource(R.drawable.matematika),
                                modifier = Modifier.size(50.dp),
                                contentScale = ContentScale.Crop
                            )

                            Spacer(modifier = Modifier.width(12.dp))

                            Column(modifier = Modifier.weight(1f)) {
                                Text(mapel.nama, fontWeight = FontWeight.Bold, color = TextPrimary)
                                Text("Nilai: ${mapel.nilai}", color = TextSecondary)
                            }

                            IconButton(onClick = { isFavorite = !isFavorite }) {
                                Icon(
                                    imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                                    contentDescription = null,
                                    tint = FavoriteRed
                                )
                            }
                        }
                    }
                }
            }
        }

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}