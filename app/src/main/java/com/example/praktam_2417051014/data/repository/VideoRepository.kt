package com.example.praktam_2417051014.data.repository

import com.example.praktam_2417051014.data.api.RetrofitClientVideo
import com.example.praktam_2417051014.data.model.Video

class VideoRepository {

    suspend fun getVideo(): List<Video> {
        return try {
            RetrofitClientVideo.api.getVideo()
        } catch (e: Exception) {
            emptyList()
        }
    }
}