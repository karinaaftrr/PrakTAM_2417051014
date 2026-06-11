package com.example.praktam_2417051014.data.model

import com.google.gson.annotations.SerializedName

data class Jenjang(

    @SerializedName("kategori")
    val kategori: String,

    @SerializedName("kelas")
    val kelas: String,

    @SerializedName("deskripsi")
    val deskripsi: String

)