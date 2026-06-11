package com.example.praktam_2417051014.data.repository

import com.example.praktam_2417051014.data.api.RetrofitClientQuizIPS
import com.example.praktam_2417051014.data.model.Quiz

class QuizRepositoryIPS {

    suspend fun getQuizIPS(
        kelas: String,
        mapel: String
    ): List<Quiz> {
        return try {
            val allQuiz = RetrofitClientQuizIPS.api.getQuizIPS()

            allQuiz.filter {
                it.kelas.trim().equals(kelas.trim(), ignoreCase = true) &&
                        it.kategori.trim().equals("IPS", ignoreCase = true) &&
                        it.mapel.trim().equals(mapel.trim(), ignoreCase = true)
            }

        } catch (e: Exception) {
            emptyList()
        }
    }
}