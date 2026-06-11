package com.example.praktam_2417051014.data.model

import com.google.gson.annotations.SerializedName

data class Video(
    @SerializedName("judul")
    val judul: String,

    @SerializedName("deskripsi")
    val deskripsi: String,

    @SerializedName("gambar")
    val gambar: String,

    @SerializedName("videoUrl")
    val videoUrl: String
)