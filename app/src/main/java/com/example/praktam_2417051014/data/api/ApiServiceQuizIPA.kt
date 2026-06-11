package com.example.praktam_2417051014.data.api

import com.example.praktam_2417051014.data.model.Quiz
import retrofit2.http.GET

interface ApiServiceQuizIPA {

    @GET("quiz_ipa_all.json")
    suspend fun getQuizIPA(): List<Quiz>
}