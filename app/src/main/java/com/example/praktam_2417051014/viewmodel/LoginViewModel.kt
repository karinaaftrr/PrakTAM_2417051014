package com.example.praktam_2417051014.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.praktam_2417051014.data.firebase.AuthRepository
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val repository = AuthRepository()

    fun register(
        username: String,
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {

        viewModelScope.launch {

            val result = repository.register(
                username = username,
                email = email,
                password = password
            )

            result.onSuccess {
                onSuccess()
            }.onFailure {
                onError(
                    it.message ?: "Registrasi gagal"
                )
            }

        }
    }

    fun login(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {

        viewModelScope.launch {

            val result = repository.login(
                email = email,
                password = password
            )

            result.onSuccess {
                onSuccess()
            }.onFailure {
                onError(
                    it.message ?: "Login gagal"
                )
            }

        }
    }

    fun resetPassword(
        email: String,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {

            val result = repository.resetPassword(
                email = email
            )

            result.onSuccess {
                onSuccess(it)
            }.onFailure {
                onError(
                    it.message ?: "Gagal mengirim link reset password"
                )
            }
        }
    }

    fun logout() {
        repository.logout()
    }

    fun isUserLoggedIn(): Boolean {
        return repository.isUserLoggedIn()
    }

    fun getUsername(): String {
        return repository.getUsername()
    }

    fun getEmail(): String {
        return repository.getEmail()
    }
}