package com.example.praktam_2417051014.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://gist.githubusercontent.com/karinaaftrr/c5ca9b2d3a67b69f813c3d1ded249b6a/raw/7d6c39ee09ea4c9c9b6035ccd9f73f0dd54494d1/"

    val instance: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}