package com.example.praktam_2417051014.data.api

import com.example.praktam_2417051014.data.model.Video
import retrofit2.http.GET

interface ApiServiceVideo {

    @GET("video.json")
    suspend fun getVideo(): List<Video>
}