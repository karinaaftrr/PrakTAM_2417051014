package com.example.praktam_2417051014.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClientQuizIPA {

    private const val BASE_URL =
        "https://gist.githubusercontent.com/karinaaftrr/a106b008f3e7ac28c900052d4d779515/raw/"

    val api: ApiServiceQuizIPA by lazy {

        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()
            .create(
                ApiServiceQuizIPA::class.java
            )

    }

}