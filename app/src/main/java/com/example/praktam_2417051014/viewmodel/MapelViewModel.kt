package com.example.praktam_2417051014.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.praktam_2417051014.data.model.Mapel
import com.example.praktam_2417051014.data.repository.MapelRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MapelViewModel : ViewModel() {

    private val repository = MapelRepository()

    private val _mapel = MutableStateFlow<List<Mapel>>(emptyList())
    val mapel: StateFlow<List<Mapel>> = _mapel

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _isError = MutableStateFlow(false)
    val isError: StateFlow<Boolean> = _isError

    init {
        getAllMapel()
    }

    private fun getAllMapel() {
        viewModelScope.launch {
            _isLoading.value = true
            _isError.value = false

            try {
                val result = repository.getMapelIPA() // fetch sekali, sudah berisi IPA + IPS

                _mapel.value = result
                _isError.value = result.isEmpty()

            } catch (e: Exception) {
                _mapel.value = emptyList()
                _isError.value = true
            }

            _isLoading.value = false
        }
    }

    fun filterMapel(kelas: String, kategori: String): List<Mapel> {
        return _mapel.value.filter {
            it.kelas.trim().equals(kelas.trim(), ignoreCase = true) &&
                    it.kategori.trim().equals(kategori.trim(), ignoreCase = true)
        }
    }

    fun refresh() {
        getAllMapel()
    }
}