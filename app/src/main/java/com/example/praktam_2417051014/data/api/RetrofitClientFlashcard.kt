package com.example.praktam_2417051014.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClientFlashcard {

    private const val BASE_URL =
        "https://gist.githubusercontent.com/karinaaftrr/40568775381cca7544bb472111a67509/raw/d51d1ef6e7b5d48d1c71e4ff59f262d466138d02/"

    val api: ApiServiceFlashcard by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServiceFlashcard::class.java)
    }
}