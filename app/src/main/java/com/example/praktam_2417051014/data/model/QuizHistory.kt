package com.example.praktam_2417051014.data.model

data class QuizHistory(
    val kelas: String = "",
    val kategori: String = "",
    val mapel: String = "",
    val skor: Int = 0,
    val benar: Int = 0,
    val salah: Int = 0,
    val totalSoal: Int = 0,
    val waktuPengerjaan: String = "",
    val createdAt: Long = System.currentTimeMillis()
)