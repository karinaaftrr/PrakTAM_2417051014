package com.example.praktam_2417051014.data.repository

import com.example.praktam_2417051014.data.api.RetrofitClientFlashcard
import com.example.praktam_2417051014.data.model.FlashCard

class FlashcardRepository {

    suspend fun getAllFlashcards(): List<FlashCard> {
        return RetrofitClientFlashcard.api.getFlashcards()
    }

    suspend fun getFlashcardSession(): List<FlashCard> {
        return getAllFlashcards()
            .shuffled()
            .take(10)
    }
}