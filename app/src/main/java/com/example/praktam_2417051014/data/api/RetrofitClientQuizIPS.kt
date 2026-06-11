package com.example.praktam_2417051014.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClientQuizIPS {

    private const val BASE_URL =
        "https://gist.githubusercontent.com/karinaaftrr/64f10bd5f7247f9232e4e25c8efb1640/raw/"

    val api: ApiServiceQuizIPS by lazy {

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()
            .create(
                ApiServiceQuizIPS::class.java
            )
    }
}