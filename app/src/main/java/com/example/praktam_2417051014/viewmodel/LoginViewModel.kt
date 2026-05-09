package com.example.praktam_2417051014.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.praktam_2417051014.data.repository.LoginRepository
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val repository = LoginRepository()

    fun login(
        username: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {

            val result = repository.getLogin()

            result.onSuccess { user ->

                if (
                    username.trim() == user.username.trim() &&
                    password.trim() == user.password.trim()
                ) {
                    onSuccess()
                } else {
                    onError("Username atau password salah")
                }

            }.onFailure {
                onError("Error koneksi / gagal ambil data")
            }
        }
    }
}