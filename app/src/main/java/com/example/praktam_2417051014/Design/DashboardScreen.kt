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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.praktam_2417051014.model.MapelSource
import com.example.praktam_2417051014.R

@Composable
fun DashboardScreen(innerPadding: PaddingValues) {

    val listMapel = MapelSource.dummyMapel

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(Color(0xFFF5F7FF))
            .padding(innerPadding),
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
                            listOf(Color(0xFF6A8DFF), Color(0xFF3F51B5))
                        )
                    )
                    .padding(20.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "BasicKuizz",
                            color = Color.White,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Mata Pelajaran MIPA",
                            color = Color.White,
                            fontSize = 14.sp
                        )
                    }

                    Icon(
                        painter = painterResource(id = R.drawable.simbol_basic),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(40.dp)
                    )
                }
            }
        }

        item {
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                Text(
                    text = "Hi, Karina Fitriamalia",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                Text(
                    text = "Pilih materi terlebih dahulu untuk mengerjakan soal",
                    fontSize = 16.sp,
                    color = Color.Gray
                )
            }
        }

        item {
            Text(
                text = "Kelas Terakhir",
                modifier = Modifier.padding(start = 16.dp),
                fontWeight = FontWeight.Bold
            )

            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(MapelSource.dummyMapel) { mapel ->

                    Card(
                        modifier = Modifier
                            .width(200.dp)
                            .height(180.dp),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = getMapelColor(mapel.nama)
                        )
                    ) {

                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(12.dp),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {

                            Image(
                                painter = painterResource(id = mapel.imageRes),
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(80.dp),
                                contentScale = ContentScale.Fit
                            )

                            Column {
                                Text(
                                    text = mapel.nama,
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold
                                )

                                Text(
                                    text = "40 Soal",
                                    color = Color.White.copy(0.8f),
                                    fontSize = 12.sp
                                )
                            }

                            Button(
                                onClick = {},
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(36.dp),
                                shape = RoundedCornerShape(10.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.White
                                )
                            ) {
                                Text(
                                    "Mulai Quiz",
                                    color = Color.Black,
                                    fontSize = 12.sp
                                )
                            }
                        }
                    }
                }
            }
        }

        item {
            Text(
                text = "Semua Materi",
                modifier = Modifier.padding(start = 16.dp),
                fontWeight = FontWeight.Bold
            )
        }

        items(listMapel) { mapel ->

            var isFavorite by remember { mutableStateOf(false) }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = getMapelColor(mapel.nama)
                ),
                shape = RoundedCornerShape(18.dp)
            ) {

                Column(modifier = Modifier.padding(20.dp)) {

                    Box {
                        Row(verticalAlignment = Alignment.CenterVertically) {

                            Box(
                                modifier = Modifier
                                    .size(80.dp)
                                    .clip(RoundedCornerShape(14.dp))
                                    .background(Color.White.copy(alpha = 0.2f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(id = mapel.imageRes),
                                    contentDescription = null,
                                    modifier = Modifier.size(50.dp),
                                    contentScale = ContentScale.Fit
                                )
                            }

                            Spacer(modifier = Modifier.width(16.dp))

                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = mapel.nama,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                                Text(
                                    text = mapel.deskripsi,
                                    color = Color.White.copy(0.8f)
                                )
                            }

                            Text(
                                text = "Kls ${mapel.kelas}",
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        IconButton(
                            onClick = { isFavorite = !isFavorite },
                            modifier = Modifier.align(Alignment.TopEnd)
                        ) {
                            Icon(
                                imageVector = if (isFavorite)
                                    Icons.Filled.Favorite
                                else
                                    Icons.Outlined.FavoriteBorder,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Button(
                        onClick = {},
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(45.dp),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White
                        )
                    ) {
                        Text(
                            "Mulai Quiz",
                            color = Color.Black,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }
    }
}

fun getMapelColor(nama: String): Color {
    return when (nama) {
        "Biologi" -> Color(0xFF4CAF50)
        "Kimia" -> Color(0xFFFFC107)
        "Fisika" -> Color(0xFF607D8B)
        "Matematika" -> Color(0xFFE53935)
        else -> Color(0xFF3F51B5)
    }
}