package com.example.praktam_2417051014.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClientVideo {

    private const val BASE_URL =
        "https://gist.githubusercontent.com/karinaaftrr/71df893e8048f390c0337ff93076438f/raw/a9ee58363fa24b2f84159f758eba074721fdb083/"

    val api: ApiServiceVideo by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServiceVideo::class.java)
    }
}