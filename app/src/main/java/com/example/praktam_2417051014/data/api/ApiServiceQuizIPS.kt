package com.example.praktam_2417051014.data.api

import com.example.praktam_2417051014.data.model.Quiz
import retrofit2.http.GET

interface ApiServiceQuizIPS {

    @GET("quiz_ips_all.json")
    suspend fun getQuizIPS(): List<Quiz>
}