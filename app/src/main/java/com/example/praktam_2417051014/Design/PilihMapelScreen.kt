package com.example.praktam_2417051014.Design

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.praktam_2417051014.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun PilihMapelScreen(namaKelas: String, innerPadding: PaddingValues) {
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    val listMapel = listOf(
        Pair("Matematika", R.drawable.matematika),
        Pair("Biologi", R.drawable.biologi),
        Pair("Fisika", R.drawable.fisika),
        Pair("Kimia", R.drawable.kimia)
    )

    Box(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Text(
                    text = "Pilih Mata Pelajaran - $namaKelas",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            items(listMapel) { mapel ->
                var isLoadingItem by remember { mutableStateOf(false) }

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.secondaryContainer
                    )
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = mapel.second),
                            contentDescription = null,
                            modifier = Modifier.size(50.dp)
                        )

                        Spacer(modifier = Modifier.width(16.dp))

                        Text(
                            text = mapel.first,
                            modifier = Modifier.weight(1f),
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.SemiBold
                        )

                        Button(
                            onClick = {
                                coroutineScope.launch {
                                    isLoadingItem = true
                                    delay(1500)
                                    isLoadingItem = false
                                    snackbarHostState.showSnackbar("Siap! Kuis ${mapel.first} segera dimulai.")
                                }
                            },
                            enabled = !isLoadingItem,
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            if (isLoadingItem) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(18.dp),
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    strokeWidth = 2.dp
                                )
                            } else {
                                Text("Pilih")
                            }
                        }
                    }
                }
            }
        }

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 16.dp)
        )
    }
}