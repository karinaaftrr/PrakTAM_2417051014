package com.example.praktam_2417051014.data.api

import com.example.praktam_2417051014.data.model.ResponseData
import retrofit2.http.GET

interface ApiServiceDashboard {
    @GET("data.json")
    suspend fun getData(): ResponseData
}