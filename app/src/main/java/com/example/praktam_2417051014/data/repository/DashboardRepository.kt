package com.example.praktam_2417051014.data.repository

import com.example.praktam_2417051014.data.api.RetrofitClientDashboard
import com.example.praktam_2417051014.data.model.ResponseData

class DashboardRepository {
    suspend fun getData(): ResponseData? {
        return try {
            RetrofitClientDashboard.api.getData()
        } catch (_: Exception) {
            null
        }
    }
}