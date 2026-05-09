package com.example.praktam_2417051014.data.api

import com.example.praktam_2417051014.data.model.Login
import retrofit2.http.GET

interface ApiServiceLogin {

    @GET("login.json")
    suspend fun getLoginData(): Login
}