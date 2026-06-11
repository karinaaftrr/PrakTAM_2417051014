package com.example.praktam_2417051014.data.api

import com.example.praktam_2417051014.data.model.FlashCard
import retrofit2.http.GET

interface ApiServiceFlashcard {

    @GET("flashcards.json")
    suspend fun getFlashcards(): List<FlashCard>
}