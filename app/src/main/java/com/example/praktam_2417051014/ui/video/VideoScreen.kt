package com.example.praktam_2417051014.ui.video

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.praktam_2417051014.data.model.Video
import com.example.praktam_2417051014.data.repository.VideoRepository
import com.example.praktam_2417051014.ui.navigation.BottomNavBar
import com.example.praktam_2417051014.ui.theme.AppBackground
import com.example.praktam_2417051014.ui.theme.BorderSoft
import com.example.praktam_2417051014.ui.theme.CardWhite
import com.example.praktam_2417051014.ui.theme.FavoriteRed
import com.example.praktam_2417051014.ui.theme.PrimaryColor
import com.example.praktam_2417051014.ui.theme.TextPrimary
import com.example.praktam_2417051014.ui.theme.TextSecondary
import kotlinx.coroutines.launch

@Composable
fun VideoScreen(
    navController: NavHostController
) {

    val context =
        LocalContext.current

    val scope =
        rememberCoroutineScope()

    var videoList by remember {
        mutableStateOf<List<Video>>(
            emptyList()
        )
    }

    var isLoading by remember {
        mutableStateOf(true)
    }

    var isError by remember {
        mutableStateOf(false)
    }

    val likedVideos =
        remember {
            mutableStateListOf<String>()
        }

    LaunchedEffect(Unit) {

        scope.launch {

            isLoading = true

            try {

                videoList =
                    VideoRepository()
                        .getVideo()

                isError =
                    videoList.isEmpty()

            } catch (e: Exception) {

                isError = true

            }

            isLoading = false
        }

    }

    Scaffold(

        containerColor =
            AppBackground,

        bottomBar = {

            BottomNavBar(
                navController =
                    navController,

                currentRoute =
                    "video"
            )

        }

    ) { paddingValues ->

        LazyColumn(

            modifier =
                Modifier
                    .fillMaxSize()
                    .background(
                        AppBackground
                    )
                    .padding(
                        paddingValues
                    )
                    .padding(
                        horizontal = 22.dp
                    ),

            contentPadding =
                PaddingValues(
                    bottom = 24.dp
                )

        ) {

            item {

                Spacer(
                    modifier =
                        Modifier.height(
                            40.dp
                        )
                )

                Text(

                    text =
                        "Video Pembelajaran",

                    fontSize =
                        30.sp,

                    fontWeight =
                        FontWeight.Bold,

                    color =
                        TextPrimary

                )

                Spacer(
                    modifier =
                        Modifier.height(
                            6.dp
                        )
                )

                Text(

                    text =
                        "Tonton video belajar pilihan.",

                    fontSize =
                        15.sp,

                    color =
                        TextSecondary

                )

                Spacer(
                    modifier =
                        Modifier.height(
                            24.dp
                        )
                )

            }

            if (isLoading) {

                item {

                    Box(

                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .padding(
                                    60.dp
                                ),

                        contentAlignment =
                            Alignment.Center

                    ) {

                        CircularProgressIndicator(
                            color =
                                PrimaryColor
                        )

                    }

                }

            }

            if (isError) {

                item {

                    Text(

                        text =
                            "Video belum tersedia",

                        color =
                            FavoriteRed,

                        fontSize =
                            15.sp

                    )

                }

            }

            items(videoList) { item ->

                val isLiked =
                    likedVideos.contains(
                        item.judul
                    )

                Card(

                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .height(
                                220.dp
                            )
                            .padding(
                                bottom = 20.dp
                            ),

                    shape =
                        RoundedCornerShape(
                            28.dp
                        ),

                    colors =
                        CardDefaults
                            .cardColors(
                                containerColor =
                                    CardWhite
                            )

                ) {

                    Box(
                        modifier =
                            Modifier
                                .fillMaxSize()
                    ) {

                        AsyncImage(

                            model =
                                item.gambar,

                            contentDescription =
                                item.judul,

                            modifier =
                                Modifier
                                    .fillMaxSize(),

                            contentScale =
                                ContentScale.Crop

                        )

                        Box(

                            modifier =
                                Modifier
                                    .fillMaxSize()
                                    .background(

                                        Brush.verticalGradient(

                                            colors =
                                                listOf(
                                                    Color.Transparent,
                                                    Color.Black.copy(
                                                        alpha = 0.75f
                                                    )
                                                )

                                        )

                                    )

                        )

                        Row(
                            modifier =
                                Modifier
                                    .align(
                                        Alignment.TopEnd
                                    )
                                    .padding(
                                        14.dp
                                    ),
                            verticalAlignment =
                                Alignment.CenterVertically
                        ) {

                            IconButton(
                                onClick = {

                                    if (isLiked) {
                                        likedVideos.remove(
                                            item.judul
                                        )
                                    } else {
                                        likedVideos.add(
                                            item.judul
                                        )
                                    }

                                },
                                modifier =
                                    Modifier
                                        .size(
                                            42.dp
                                        )
                                        .clip(
                                            CircleShape
                                        )
                                        .background(
                                            CardWhite.copy(
                                                alpha = 0.9f
                                            )
                                        )
                                        .border(
                                            width = 1.dp,
                                            color =
                                                if (isLiked) {
                                                    FavoriteRed.copy(
                                                        alpha = 0.45f
                                                    )
                                                } else {
                                                    BorderSoft
                                                },
                                            shape = CircleShape
                                        )
                            ) {

                                Icon(
                                    imageVector =
                                        if (isLiked) {
                                            Icons.Filled.Favorite
                                        } else {
                                            Icons.Outlined.FavoriteBorder
                                        },
                                    contentDescription =
                                        "Tombol Like",
                                    tint =
                                        if (isLiked) {
                                            FavoriteRed
                                        } else {
                                            TextSecondary
                                        },
                                    modifier =
                                        Modifier.size(
                                            23.dp
                                        )
                                )

                            }

                            Spacer(
                                modifier =
                                    Modifier.size(
                                        8.dp
                                    )
                            )

                            Button(

                                onClick = {

                                    context.startActivity(

                                        Intent(
                                            Intent.ACTION_VIEW,

                                            Uri.parse(
                                                item.videoUrl
                                            )

                                        )

                                    )

                                },

                                shape =
                                    RoundedCornerShape(
                                        50.dp
                                    ),

                                colors =
                                    ButtonDefaults.buttonColors(
                                        containerColor =
                                            PrimaryColor,
                                        contentColor =
                                            CardWhite
                                    ),

                                contentPadding =
                                    PaddingValues(
                                        horizontal = 16.dp,
                                        vertical = 8.dp
                                    )

                            ) {

                                Text(
                                    text =
                                        "Tonton",
                                    fontSize =
                                        13.sp,
                                    fontWeight =
                                        FontWeight.Bold
                                )

                            }

                        }

                        Column(

                            modifier =
                                Modifier
                                    .align(
                                        Alignment.BottomStart
                                    )
                                    .padding(
                                        22.dp
                                    )

                        ) {

                            Text(

                                text =
                                    item.judul,

                                fontSize =
                                    22.sp,

                                fontWeight =
                                    FontWeight.Bold,

                                color =
                                    CardWhite

                            )

                            Spacer(
                                modifier =
                                    Modifier.height(
                                        8.dp
                                    )
                            )

                            Text(

                                text =
                                    item.deskripsi,

                                fontSize =
                                    13.sp,

                                color =
                                    CardWhite

                            )

                            Spacer(
                                modifier =
                                    Modifier.height(
                                        6.dp
                                    )
                            )

                            Text(
                                text =
                                    if (isLiked) {
                                        "Video disukai"
                                    } else {
                                        "Tekan love untuk menyukai video"
                                    },
                                fontSize =
                                    12.sp,
                                color =
                                    if (isLiked) {
                                        FavoriteRed
                                    } else {
                                        CardWhite.copy(
                                            alpha = 0.85f
                                        )
                                    }
                            )

                        }

                    }

                }

            }

        }

    }

}