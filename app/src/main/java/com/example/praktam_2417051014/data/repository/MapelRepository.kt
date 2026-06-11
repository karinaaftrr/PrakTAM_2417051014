package com.example.praktam_2417051014.data.repository

import com.example.praktam_2417051014.data.api.RetrofitClientMapel
import com.example.praktam_2417051014.data.model.Mapel

class MapelRepository {

    suspend fun getMapelIPA(): List<Mapel> {
        return try {
            RetrofitClientMapel.api.getMapel()
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getMapelIPS(): List<Mapel> {
        return try {
            RetrofitClientMapel.api.getMapel()
        } catch (e: Exception) {
            emptyList()
        }
    }
}