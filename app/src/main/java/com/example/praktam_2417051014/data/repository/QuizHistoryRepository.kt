package com.example.praktam_2417051014.data.firebase

import com.example.praktam_2417051014.data.model.QuizHistory
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class QuizHistoryRepository {

    private val auth = FirebaseAuth.getInstance()
    private val firestore = FirebaseFirestore.getInstance()

    suspend fun saveQuizHistory(
        history: QuizHistory
    ): Result<String> {
        return try {
            val uid = auth.currentUser?.uid
                ?: return Result.failure(Exception("User belum login"))

            firestore
                .collection("users")
                .document(uid)
                .collection("quiz_history")
                .add(history)
                .await()

            Result.success("Hasil quiz berhasil disimpan")
        } catch (e: Exception) {
            Result.failure(Exception(e.message ?: "Gagal menyimpan hasil quiz"))
        }
    }
}