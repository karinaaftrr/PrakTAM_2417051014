package com.example.praktam_2417051014.data.model

import com.google.gson.annotations.SerializedName

data class Login(
    @SerializedName("username")
    val username: String,

    @SerializedName("password")
    val password: String
)