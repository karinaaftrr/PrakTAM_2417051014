package com.example.praktam_2417051014.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.praktam_2417051014.data.model.Video
import com.example.praktam_2417051014.data.model.Jenjang
import com.example.praktam_2417051014.data.repository.DashboardRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DashboardViewModel : ViewModel() {

    private val repository = DashboardRepository()

    private val _jenjang = MutableStateFlow<List<Jenjang>>(emptyList())
    val jenjang: StateFlow<List<Jenjang>> = _jenjang

    private val _vidio = MutableStateFlow<List<Video>>(emptyList())
    val vidio : StateFlow<List<Video>> = _vidio

    private val _selectedKategori = MutableStateFlow("IPA")
    val selectedKategori: StateFlow<String> = _selectedKategori

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _isError = MutableStateFlow(false)
    val isError: StateFlow<Boolean> = _isError

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            _isLoading.value = true
            _isError.value = false

            val result = repository.getData()

            if (result != null) {
                _jenjang.value = result.jenjang
                _vidio.value = result.artikel
                _isError.value = false
            } else {
                _jenjang.value = emptyList()
                _vidio.value = emptyList()
                _isError.value = true
            }

            _isLoading.value = false
        }
    }

    fun pilihKategori(kategori: String) {
        _selectedKategori.value = kategori
    }

}