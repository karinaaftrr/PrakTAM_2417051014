package com.example.praktam_2417051014.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.praktam_2417051014.data.model.MataPelajaran
import com.example.praktam_2417051014.data.repository.MapelRepository
import kotlinx.coroutines.launch

class MapelViewModel : ViewModel() {

    private val repository = MapelRepository()

    var listMapel by mutableStateOf<List<MataPelajaran>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    var isError by mutableStateOf(false)
        private set

    var isEmpty by mutableStateOf(false)
        private set

    init {
        getMapel()
    }

    fun getMapel() {

        viewModelScope.launch {

            isLoading = true
            isError = false
            isEmpty = false

            try {

                val data = repository.getMapel()
                listMapel = data

                isEmpty = data.isEmpty()

            } catch (_: Exception) {

                isError = true
            }

            isLoading = false
        }
    }
}