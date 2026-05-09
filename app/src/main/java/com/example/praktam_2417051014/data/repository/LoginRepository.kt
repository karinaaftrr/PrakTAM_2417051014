package com.example.praktam_2417051014.data.repository

import com.example.praktam_2417051014.data.api.RetrofitClientLogin
import com.example.praktam_2417051014.data.model.Login

class LoginRepository {

    suspend fun getLogin(): Result<Login> {
        return try {
            val response = RetrofitClientLogin.instance.getLoginData()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}