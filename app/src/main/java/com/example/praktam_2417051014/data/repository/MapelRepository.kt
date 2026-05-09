package com.example.praktam_2417051014.data.repository

import com.example.praktam_2417051014.data.api.RetrofitClient
import com.example.praktam_2417051014.data.model.MataPelajaran

class MapelRepository {

    suspend fun getMapel(): List<MataPelajaran> {

        return try {

            RetrofitClient.instance.getMapels()

        } catch (_: Exception) {

            emptyList()
        }
    }
}