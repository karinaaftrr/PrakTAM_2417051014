package com.example.praktam_2417051014.Design

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.praktam_2417051014.R
import com.example.praktam_2417051014.model.MataPelajaran
import com.example.praktam_2417051014.network.RetrofitClient
import com.example.praktam_2417051014.ui.theme.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun PilihMapelScreen(
    navController: NavController,
    namaKelas: String,
    innerPadding: PaddingValues
) {
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    var listMapel by remember { mutableStateOf<List<MataPelajaran>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    var isError by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        try {
            listMapel = RetrofitClient.instance.getMapels()
            isLoading = false
            isError = false
            snackbarHostState.showSnackbar("Data berhasil dimuat")
        } catch (e: Exception) {
            isLoading = false
            isError = true
            snackbarHostState.showSnackbar("Gagal memuat data")
            Log.e("PilihMapelScreen", "Error: ${e.message}")
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppBackground)
            .padding(innerPadding)
    ) {
        when {
            isLoading -> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = BluePrimary)
                }
            }

            isError -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Gagal Memuat Data",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.Red
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Pastikan koneksi internet Anda menyala",
                        textAlign = TextAlign.Center
                    )
                }
            }

            else -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    item {
                        Text(
                            text = "Pilih Mata Pelajaran - $namaKelas",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )
                    }

                    items(listMapel) { mapel ->
                        MapelItem(
                            mapel = mapel,
                            onPilihClick = {
                                coroutineScope.launch {
                                    snackbarHostState.showSnackbar("Kuis ${mapel.nama} dimulai!")
                                }
                            }
                        )
                    }
                }
            }
        }

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
        )
    }
}

@Composable
fun MapelItem(
    mapel: MataPelajaran,
    onPilihClick: () -> Unit
) {
    var isLoadingItem by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardWhite),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = mapel.imageUrl,
                contentDescription = mapel.nama,
                placeholder = painterResource(R.drawable.matematika),
                error = painterResource(R.drawable.matematika),
                modifier = Modifier.size(50.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = mapel.nama,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = TextPrimary
                )
                Text(
                    text = "Nilai: ${mapel.nilai}",
                    style = MaterialTheme.typography.bodySmall,
                    color = TextSecondary
                )
            }

            Button(
                onClick = {
                    scope.launch {
                        isLoadingItem = true
                        delay(1500)
                        isLoadingItem = false
                        onPilihClick()
                    }
                },
                enabled = !isLoadingItem,
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = BluePrimary,
                    contentColor = TextPrimary
                )
            ) {
                if (isLoadingItem) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(18.dp),
                        color = TextPrimary,
                        strokeWidth = 2.dp
                    )
                } else {
                    Text("Pilih", fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}