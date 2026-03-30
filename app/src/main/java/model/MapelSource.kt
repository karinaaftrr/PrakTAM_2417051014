package com.example.praktam_2417051014.model

import com.example.praktam_2417051014.R

object MapelSource {

    val dummyMapel = listOf(
        MataPelajaran(
            nama = "Matematika",
            deskripsi = "Relasi dan Fungsi",
            kelas = 10,
            nilai = "90/100",
            imageRes = R.drawable.matematika
        ),
        MataPelajaran(
            nama = "Fisika",
            deskripsi = "Hukum Newton",
            kelas = 11,
            nilai = "70/100",
            imageRes = R.drawable.fisika
        ),
        MataPelajaran(
            nama = "Kimia",
            deskripsi = "Reaksi Kimia",
            kelas = 10,
            nilai = "85/100",
            imageRes = R.drawable.kimia
        ),
        MataPelajaran(
            nama = "Biologi",
            deskripsi = "Sistem Organ",
            kelas = 11,
            nilai = "88/100",
            imageRes = R.drawable.biologi
        )
    )
}