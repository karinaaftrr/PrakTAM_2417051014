package com.example.praktam_2417051014.data.model
import com.google.gson.annotations.SerializedName

data class FlashCard(
    @SerializedName("id")
    val id: Int,

    @SerializedName("kelas")
    val kelas: String,

    @SerializedName("kategori")
    val kategori: String,

    @SerializedName("mapel")
    val mapel: String,

    @SerializedName("pertanyaan")
    val pertanyaan: String,

    @SerializedName("jawaban")
    val jawaban: String,

    @SerializedName("pembahasan")
    val pembahasan: String
)
