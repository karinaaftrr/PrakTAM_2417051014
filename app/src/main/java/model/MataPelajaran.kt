package com.example.praktam_2417051014.model
import androidx.annotation.DrawableRes

data class MataPelajaran(
    val nama: String,
    val deskripsi: String,
    val kelas: Int,
    val nilai: String,
    @DrawableRes val imageRes: Int
)