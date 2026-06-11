package com.example.praktam_2417051014.data.model

import com.google.gson.annotations.SerializedName

data class Mapel(

    @SerializedName("kelas")
    val kelas: String,

    @SerializedName("kategori")
    val kategori: String,

    @SerializedName("nama")
    val nama: String,

    @SerializedName("deskripsi")
    val deskripsi: String,

    @SerializedName("jumlahQuiz")
    val jumlahQuiz: String,

    @SerializedName("gambar")
    val gambar: String

)