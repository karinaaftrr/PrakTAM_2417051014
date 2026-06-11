package com.example.praktam_2417051014.data.firebase

import com.example.praktam_2417051014.data.model.QuizHistory
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class QuizHistoryReader {

    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    suspend fun getQuizHistory(): List<QuizHistory> {
        return try {
            val uid = auth.currentUser?.uid ?: return emptyList()

            val result = firestore
                .collection("users")
                .document(uid)
                .collection("quiz_history")
                .get()
                .await()

            result.documents
                .map { document ->
                    QuizHistory(
                        kelas = document.getString("kelas") ?: "",
                        kategori = document.getString("kategori") ?: "",
                        mapel = document.getString("mapel") ?: "",
                        skor = document.getLong("skor")?.toInt() ?: 0,
                        benar = document.getLong("benar")?.toInt() ?: 0,
                        salah = document.getLong("salah")?.toInt() ?: 0,
                        totalSoal = document.getLong("totalSoal")?.toInt() ?: 0,
                        waktuPengerjaan = document.getString("waktuPengerjaan") ?: "",
                        createdAt = document.getLong("createdAt") ?: 0L
                    )
                }
                .sortedByDescending { it.createdAt }

        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getLatestScore(
        kelas: String,
        kategori: String,
        mapel: String
    ): Int? {
        return try {
            val uid = auth.currentUser?.uid ?: return null

            val result = firestore
                .collection("users")
                .document(uid)
                .collection("quiz_history")
                .whereEqualTo("kelas", kelas)
                .whereEqualTo("kategori", kategori)
                .whereEqualTo("mapel", mapel)
                .get()
                .await()

            result.documents
                .maxByOrNull { it.getLong("createdAt") ?: 0L }
                ?.getLong("skor")
                ?.toInt()

        } catch (e: Exception) {
            null
        }
    }
}