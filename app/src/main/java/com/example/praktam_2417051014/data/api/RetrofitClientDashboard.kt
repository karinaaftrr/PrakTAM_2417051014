package com.example.praktam_2417051014.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClientDashboard {

    private const val BASE_URL =
        "https://gist.githubusercontent.com/karinaaftrr/49523fe5a629facc77c8a2be565890b1/raw/d12a6abfdada25bc649e111e71e120dc49779f84/"

    val api: ApiServiceDashboard by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServiceDashboard::class.java)
    }
}