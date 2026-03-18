package com.example.praktam_2417051014.Design

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F7FF))
            .padding(innerPadding)
            .verticalScroll(rememberScrollState())
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFF6A8DFF),
                            Color(0xFF3F51B5)
                        )
                    )
                )
                .padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
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

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 28.dp)
        ) {

            Text(
                text = "Hi, Karina Fitriamalia",
                fontSize = 26.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Black
            )

            Text(
                text = "Pilih materi terlebih dahulu untuk mengerjakan soal",
                fontSize = 18.sp,
                color = Color.Gray
            )
        }

        Column(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            listMapel.forEach { mapel ->

                var isFavorite by remember { mutableStateOf(false) }

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF2F4FB2)
                    ),
                    shape = RoundedCornerShape(18.dp)
                ) {

                    Column(
                        modifier = Modifier.padding(26.dp)
                    ) {

                        Box(
                            modifier = Modifier.fillMaxWidth()
                        ) {

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                Box(
                                    modifier = Modifier
                                        .size(100.dp)
                                        .clip(RoundedCornerShape(14.dp))
                                        .background(Color.White.copy(alpha = 0.2f)),
                                    contentAlignment = Alignment.Center
                                ) {

                                    Image(
                                        painter = painterResource(id = mapel.imageRes),
                                        contentDescription = mapel.nama,
                                        modifier = Modifier.size(70.dp),
                                        contentScale = ContentScale.Fit
                                    )
                                }

                                Spacer(modifier = Modifier.width(24.dp))

                                Column(modifier = Modifier.weight(1f)) {

                                    Text(
                                        text = mapel.nama,
                                        fontSize = 24.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )

                                    Text(
                                        text = mapel.deskripsi,
                                        fontSize = 18.sp,
                                        color = Color.White.copy(alpha = 0.8f)
                                    )
                                }

                                Text(
                                    text = "Kls ${mapel.kelas}",
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            }

                            IconButton(
                                onClick = { isFavorite = !isFavorite },
                                modifier = Modifier.align(Alignment.TopEnd)
                            ) {
                                Icon(
                                    imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                                    contentDescription = null,
                                    tint = if (isFavorite) Color.Red else Color.White
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            onClick = { },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(55.dp),
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF1E3A8A)
                            )
                        ) {
                            Text(
                                text = "Mulai Quiz",
                                color = Color.White,
                                fontSize = 18.sp
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(40.dp))
    }
}