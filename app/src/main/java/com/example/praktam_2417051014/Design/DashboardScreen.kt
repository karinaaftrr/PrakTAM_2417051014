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
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.praktam_2417051014.R
import com.example.praktam_2417051014.data.model.MataPelajaran
import com.example.praktam_2417051014.viewmodel.MapelViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import com.example.praktam_2417051014.ui.theme.AppBackground
import com.example.praktam_2417051014.ui.theme.PrimaryColor
import com.example.praktam_2417051014.ui.theme.SecondaryColor
import com.example.praktam_2417051014.ui.theme.TextPrimary
import com.example.praktam_2417051014.ui.theme.TextSecondary

@Composable
fun DashboardScreen(
    innerPadding: PaddingValues,
    onNavigate: (String) -> Unit
) {

    val viewModel: MapelViewModel = viewModel()

    val listMapel = viewModel.listMapel
    val isLoadingData = viewModel.isLoading

    val loadingState = remember { mutableStateMapOf<String, Boolean>() }
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    val kelasList = listOf(
        Pair("Kelas 10", R.drawable.kelas10),
        Pair("Kelas 11", R.drawable.kelas11),
        Pair("Kelas 12", R.drawable.kelas12)
    )

    Scaffold(
        containerColor = AppBackground,
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(AppBackground)
                .padding(padding)
        ) {

            if (isLoadingData) {

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = PrimaryColor)
                }

            } else {

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(bottom = 24.dp)
                ) {

                    item {

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                                .clip(RoundedCornerShape(24.dp))
                                .background(
                                    Brush.horizontalGradient(
                                        listOf(PrimaryColor, SecondaryColor)
                                    )
                                )
                                .padding(20.dp)
                        ) {

                            Column {

                                Text(
                                    "BasicKuizz",
                                    style = MaterialTheme.typography.titleLarge,
                                    color = androidx.compose.ui.graphics.Color.White
                                )

                                Text(
                                    "Mata Pelajaran Kurikulum Merdeka",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = androidx.compose.ui.graphics.Color.White
                                )
                            }
                        }
                    }

                    item {

                        Column(Modifier.padding(horizontal = 16.dp)) {

                            Text(
                                "Hi, Karina Fitriamalia",
                                style = MaterialTheme.typography.headlineMedium,
                                color = TextPrimary
                            )

                            Text(
                                "Mari mengerjakan soal!!",
                                style = MaterialTheme.typography.bodyMedium,
                                color = TextSecondary
                            )
                        }
                    }

                    item {

                        Text(
                            "Pilih Kelas",
                            modifier = Modifier.padding(start = 16.dp),
                            style = MaterialTheme.typography.titleMedium,
                            color = TextPrimary
                        )

                        LazyRow(
                            contentPadding = PaddingValues(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {

                            items(kelasList) { kelas ->

                                val isLoading = loadingState[kelas.first] == true

                                Card(
                                    modifier = Modifier
                                        .width(200.dp)
                                        .height(190.dp),
                                    shape = RoundedCornerShape(20.dp),
                                    colors = CardDefaults.cardColors(containerColor = PrimaryColor)
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
                                            style = MaterialTheme.typography.titleSmall,
                                            color = androidx.compose.ui.graphics.Color.White
                                        )

                                        Button(
                                            onClick = {
                                                coroutineScope.launch {
                                                    loadingState[kelas.first] = true
                                                    kotlinx.coroutines.delay(1200)
                                                    loadingState[kelas.first] = false
                                                    snackbarHostState.showSnackbar("Masuk ${kelas.first}")
                                                    onNavigate("pilih_mapel/${kelas.first}")
                                                }
                                            },
                                            modifier = Modifier.fillMaxWidth(),
                                            enabled = !isLoading,
                                            colors = ButtonDefaults.buttonColors(
                                                containerColor = androidx.compose.ui.graphics.Color.White,
                                                contentColor = PrimaryColor
                                            )
                                        ) {

                                            if (isLoading) {
                                                CircularProgressIndicator(
                                                    modifier = Modifier.size(18.dp),
                                                    color = PrimaryColor
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
                            style = MaterialTheme.typography.titleMedium,
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
                            colors = CardDefaults.cardColors(containerColor = PrimaryColor)
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

                                    Text(
                                        mapel.nama,
                                        style = MaterialTheme.typography.titleSmall,
                                        color = androidx.compose.ui.graphics.Color.White
                                    )

                                    Text(
                                        "Nilai: ${mapel.nilai}",
                                        style = MaterialTheme.typography.bodyMedium,
                                        color = androidx.compose.ui.graphics.Color.White
                                    )
                                }

                                IconButton(onClick = { isFavorite = !isFavorite }) {

                                    Icon(
                                        imageVector = if (isFavorite)
                                            Icons.Filled.Favorite
                                        else
                                            Icons.Outlined.FavoriteBorder,
                                        contentDescription = null,
                                        tint = androidx.compose.ui.graphics.Color.White
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}