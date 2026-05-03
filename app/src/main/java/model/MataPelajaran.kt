package com.example.praktam_2417051014.model

import com.google.gson.annotations.SerializedName

data class MataPelajaran(
    @SerializedName("nama") val nama: String,
    @SerializedName("deskripsi") val deskripsi: String,
    @SerializedName("kelas") val kelas: Int,
    @SerializedName("nilai") val nilai: String,
    @SerializedName("image_url")
    val imageUrl: String
)