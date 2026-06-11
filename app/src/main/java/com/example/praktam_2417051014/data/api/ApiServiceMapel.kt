package com.example.praktam_2417051014.data.api

import com.example.praktam_2417051014.data.model.Mapel
import retrofit2.http.GET

interface ApiServiceMapel {

    @GET("mapel.json")
    suspend fun getMapel(): List<Mapel>
}