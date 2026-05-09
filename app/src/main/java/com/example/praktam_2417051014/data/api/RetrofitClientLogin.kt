package com.example.praktam_2417051014.data.api
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClientLogin {

    private const val BASE_URL =
        "https://gist.githubusercontent.com/karinaaftrr/91aca329128c9b075a9c1265d8b0a970/raw/e6165d2d68074a66e3327f8430ffee1b80c7e36e/"

    val instance: ApiServiceLogin by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServiceLogin::class.java)
    }
}