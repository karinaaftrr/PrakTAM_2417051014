package com.example.praktam_2417051014.data.model

import com.google.gson.annotations.SerializedName

data class Quiz(

    @SerializedName("kelas")
    val kelas: String,

    @SerializedName("kategori")
    val kategori: String,

    @SerializedName("mapel")
    val mapel: String,

    @SerializedName("pertanyaan")
    val pertanyaan: String,

    @SerializedName("pilihan")
    val pilihan: List<String>,

    @SerializedName("jawabanBenar")
    val jawabanBenar: Int,

    @SerializedName("pembahasan")
    val pembahasan: String
)