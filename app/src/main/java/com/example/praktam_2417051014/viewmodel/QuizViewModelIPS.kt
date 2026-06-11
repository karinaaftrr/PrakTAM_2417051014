package com.example.praktam_2417051014.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.praktam_2417051014.data.firebase.QuizHistoryRepository
import com.example.praktam_2417051014.data.model.Quiz
import com.example.praktam_2417051014.data.model.QuizHistory
import com.example.praktam_2417051014.data.repository.QuizRepositoryIPS
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class QuizViewModelIPS : ViewModel() {

    private val repository = QuizRepositoryIPS()
    private val historyRepository = QuizHistoryRepository()

    private val _quizList = MutableStateFlow<List<Quiz>>(emptyList())
    val quizList: StateFlow<List<Quiz>> = _quizList

    private val _currentIndex = MutableStateFlow(0)
    val currentIndex: StateFlow<Int> = _currentIndex

    private val _selectedAnswer = MutableStateFlow<Int?>(null)
    val selectedAnswer: StateFlow<Int?> = _selectedAnswer

    private val _score = MutableStateFlow(0)
    val score: StateFlow<Int> = _score

    private val _showPembahasan = MutableStateFlow(false)
    val showPembahasan: StateFlow<Boolean> = _showPembahasan

    private val _isFinished = MutableStateFlow(false)
    val isFinished: StateFlow<Boolean> = _isFinished

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _isError = MutableStateFlow(false)
    val isError: StateFlow<Boolean> = _isError

    private val _timeLeft = MutableStateFlow(30 * 60)
    val timeLeft: StateFlow<Int> = _timeLeft

    private var timerJob: Job? = null
    private var startTimeMillis: Long = 0L
    private var isHistorySaved = false

    fun loadQuiz(
        kelas: String,
        mapel: String
    ) {
        viewModelScope.launch {
            _isLoading.value = true
            _isError.value = false

            val result = repository.getQuizIPS(
                kelas = kelas,
                mapel = mapel
            )

            if (result.isNotEmpty()) {
                _quizList.value = result
                resetState()
                startTimer()
            } else {
                _quizList.value = emptyList()
                _isError.value = true
            }

            _isLoading.value = false
        }
    }

    private fun resetState() {
        timerJob?.cancel()

        _currentIndex.value = 0
        _selectedAnswer.value = null
        _score.value = 0
        _showPembahasan.value = false
        _isFinished.value = false
        _timeLeft.value = 30 * 60

        isHistorySaved = false
        startTimeMillis = System.currentTimeMillis()
    }

    private fun startTimer() {
        timerJob?.cancel()

        timerJob = viewModelScope.launch {
            while (_timeLeft.value > 0 && !_isFinished.value) {
                delay(1000)
                _timeLeft.value = _timeLeft.value - 1
            }

            if (_timeLeft.value <= 0 && !_isFinished.value) {
                finishQuiz()
            }
        }
    }

    fun pilihJawaban(index: Int) {
        if (_selectedAnswer.value == null && !_isFinished.value) {
            _selectedAnswer.value = index
            _showPembahasan.value = true

            val currentQuestion =
                _quizList.value.getOrNull(_currentIndex.value)

            if (currentQuestion != null && index == currentQuestion.jawabanBenar) {
                _score.value += 1
            }
        }
    }

    fun nextQuestion() {
        val nextIndex = _currentIndex.value + 1

        if (nextIndex < _quizList.value.size) {
            _currentIndex.value = nextIndex
            _selectedAnswer.value = null
            _showPembahasan.value = false
        } else {
            finishQuiz()
        }
    }

    private fun finishQuiz() {
        if (_isFinished.value) return

        _isFinished.value = true
        timerJob?.cancel()
        saveHistory()
    }

    private fun saveHistory() {
        if (isHistorySaved) return

        val firstQuiz = _quizList.value.firstOrNull() ?: return
        val totalSoal = _quizList.value.size
        val benar = _score.value
        val salah = totalSoal - benar

        val history = QuizHistory(
            kelas = firstQuiz.kelas,
            kategori = firstQuiz.kategori,
            mapel = firstQuiz.mapel,
            skor = getNilai(),
            benar = benar,
            salah = salah,
            totalSoal = totalSoal,
            waktuPengerjaan = getWaktuPengerjaan(),
            createdAt = System.currentTimeMillis()
        )

        viewModelScope.launch {
            historyRepository.saveQuizHistory(history)
            isHistorySaved = true
        }
    }

    fun restartQuiz() {
        resetState()
        startTimer()
    }

    fun getNilai(): Int {
        val total = _quizList.value.size

        return if (total == 0) {
            0
        } else {
            (_score.value * 100) / total
        }
    }

    fun getWaktuPengerjaan(): String {
        val durationMillis =
            System.currentTimeMillis() - startTimeMillis

        val totalSeconds =
            durationMillis / 1000

        val minutes =
            totalSeconds / 60

        val seconds =
            totalSeconds % 60

        return "${minutes} menit ${seconds} detik"
    }

    fun getMotivasi(): String {
        val nilai = getNilai()

        return when {
            nilai >= 90 -> "Hebat! Kamu menguasai materi ini dengan sangat baik."
            nilai >= 80 -> "Bagus sekali! Sedikit lagi menuju sempurna."
            nilai >= 70 -> "Hasil yang baik. Terus latihan agar makin kuat."
            nilai >= 60 -> "Jangan menyerah, kamu sudah membuat progres."
            else -> "Tidak apa-apa, kesalahan adalah bagian dari proses belajar."
        }
    }

    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel()
    }
}