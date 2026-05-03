package com.example.praktam_2417051014.network

import retrofit2.http.GET
import com.example.praktam_2417051014.model.MataPelajaran

interface ApiService {
    @GET("mapel.json")
    suspend fun getMapels(): List<MataPelajaran>
}