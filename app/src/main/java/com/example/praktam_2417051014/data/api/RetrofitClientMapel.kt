package com.example.praktam_2417051014.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClientMapel {

    private const val BASE_URL =
        "https://gist.githubusercontent.com/karinaaftrr/3ca910979fb5534a6410e657863d73c3/raw/"

    val api: ApiServiceMapel by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServiceMapel::class.java)
    }
}