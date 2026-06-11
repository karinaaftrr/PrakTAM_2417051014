package com.example.praktam_2417051014.data.repository

import com.example.praktam_2417051014.data.api.RetrofitClientQuizIPA
import com.example.praktam_2417051014.data.model.Quiz

class QuizRepositoryIPA {

    suspend fun getQuizIPA(
        kelas: String,
        mapel: String
    ): List<Quiz> {
        return try {
            val allQuiz = RetrofitClientQuizIPA.api.getQuizIPA()

            allQuiz.filter {
                it.kelas.trim().equals(kelas.trim(), ignoreCase = true) &&
                        it.kategori.trim().equals("IPA", ignoreCase = true) &&
                        it.mapel.trim().equals(mapel.trim(), ignoreCase = true)
            }

        } catch (e: Exception) {
            emptyList()
        }
    }
}