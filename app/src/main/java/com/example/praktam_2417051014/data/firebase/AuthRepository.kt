package com.example.praktam_2417051014.data.firebase

import android.net.Uri
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.tasks.await

class AuthRepository {

    private val auth = FirebaseAuth.getInstance()

    suspend fun register(
        username: String,
        email: String,
        password: String
    ): Result<String> {

        if (username.isBlank()) {
            return Result.failure(
                Exception("Username tidak boleh kosong")
            )
        }

        if (email.isBlank()) {
            return Result.failure(
                Exception("Email tidak boleh kosong")
            )
        }

        if (password.isBlank()) {
            return Result.failure(
                Exception("Password tidak boleh kosong")
            )
        }

        if (password.length < 6) {
            return Result.failure(
                Exception("Password minimal 6 karakter")
            )
        }

        return try {
            val result =
                auth.createUserWithEmailAndPassword(
                    email.trim(),
                    password
                ).await()

            val user = result.user

            val profileUpdates =
                UserProfileChangeRequest.Builder()
                    .setDisplayName(
                        username.trim()
                    )
                    .build()

            user?.updateProfile(
                profileUpdates
            )?.await()

            Result.success(
                "Registrasi berhasil"
            )

        } catch (e: Exception) {

            val message = when {

                e.message?.contains(
                    "email address is already in use",
                    true
                ) == true ->
                    "Email sudah terdaftar"

                e.message?.contains(
                    "badly formatted",
                    true
                ) == true ->
                    "Format email tidak valid"

                e.message?.contains(
                    "least 6 characters",
                    true
                ) == true ->
                    "Password minimal 6 karakter"

                else ->
                    e.message ?: "Registrasi gagal"
            }

            Result.failure(
                Exception(message)
            )
        }
    }

    suspend fun login(
        email: String,
        password: String
    ): Result<String> {

        if (email.isBlank()) {
            return Result.failure(
                Exception("Email tidak boleh kosong")
            )
        }

        if (password.isBlank()) {
            return Result.failure(
                Exception("Password tidak boleh kosong")
            )
        }

        return try {
            auth.signInWithEmailAndPassword(
                email.trim(),
                password
            ).await()

            Result.success(
                "Login berhasil"
            )

        } catch (e: Exception) {

            val message = when {

                e.message?.contains(
                    "badly formatted",
                    true
                ) == true ->
                    "Format email tidak valid"

                e.message?.contains(
                    "password is invalid",
                    true
                ) == true ->
                    "Password salah"

                e.message?.contains(
                    "no user record",
                    true
                ) == true ->
                    "Email belum terdaftar"

                e.message?.contains(
                    "credential is incorrect",
                    true
                ) == true ->
                    "Email atau password salah"

                else ->
                    e.message ?: "Login gagal"
            }

            Result.failure(
                Exception(message)
            )
        }
    }

    fun isUserLoggedIn(): Boolean {
        return auth.currentUser != null
    }

    fun getUsername(): String {
        return auth.currentUser?.displayName ?: ""
    }

    fun getEmail(): String {
        return auth.currentUser?.email ?: ""
    }

    fun getPhotoUrl(): String {
        return auth.currentUser?.photoUrl?.toString() ?: ""
    }

    fun logout() {
        auth.signOut()
    }

    suspend fun resetPassword(
        email: String
    ): Result<String> {

        if (email.isBlank()) {
            return Result.failure(
                Exception("Email tidak boleh kosong")
            )
        }

        return try {
            auth.sendPasswordResetEmail(
                email.trim()
            ).await()

            Result.success(
                "Link reset password sudah dikirim ke email"
            )

        } catch (e: Exception) {

            val message = when {

                e.message?.contains(
                    "badly formatted",
                    true
                ) == true ->
                    "Format email tidak valid"

                e.message?.contains(
                    "no user record",
                    true
                ) == true ->
                    "Email belum terdaftar"

                else ->
                    e.message ?: "Gagal mengirim link reset password"
            }

            Result.failure(
                Exception(message)
            )
        }
    }

    suspend fun updateUsername(
        username: String
    ): Result<String> {

        if (username.isBlank()) {
            return Result.failure(
                Exception("Username tidak boleh kosong")
            )
        }

        return try {
            val user = auth.currentUser
                ?: return Result.failure(
                    Exception("User belum login")
                )

            val profileUpdates =
                UserProfileChangeRequest.Builder()
                    .setDisplayName(
                        username.trim()
                    )
                    .build()

            user.updateProfile(
                profileUpdates
            ).await()

            user.reload().await()

            Result.success(
                "Username berhasil diperbarui"
            )

        } catch (e: Exception) {
            Result.failure(
                Exception(e.message ?: "Gagal memperbarui username")
            )
        }
    }

    suspend fun updatePhotoUrl(
        photoUrl: String
    ): Result<String> {

        return try {
            val user = auth.currentUser
                ?: return Result.failure(
                    Exception("User belum login")
                )

            val profileUpdates =
                UserProfileChangeRequest.Builder()
                    .setPhotoUri(
                        if (photoUrl.isBlank()) {
                            null
                        } else {
                            Uri.parse(photoUrl.trim())
                        }
                    )
                    .build()

            user.updateProfile(
                profileUpdates
            ).await()

            user.reload().await()

            Result.success(
                "Foto profil berhasil diperbarui"
            )

        } catch (e: Exception) {
            Result.failure(
                Exception(e.message ?: "Gagal memperbarui foto profil")
            )
        }
    }

    suspend fun updateEmail(
        email: String
    ): Result<String> {

        if (email.isBlank()) {
            return Result.failure(
                Exception("Email tidak boleh kosong")
            )
        }

        return try {
            val user = auth.currentUser
                ?: return Result.failure(
                    Exception("User belum login")
                )

            user.verifyBeforeUpdateEmail(
                email.trim()
            ).await()

            Result.success(
                "Link verifikasi sudah dikirim ke email baru"
            )

        } catch (e: Exception) {

            val message = when {

                e.message?.contains(
                    "badly formatted",
                    true
                ) == true ->
                    "Format email tidak valid"

                e.message?.contains(
                    "requires recent login",
                    true
                ) == true ->
                    "Silakan login ulang sebelum mengganti email"

                e.message?.contains(
                    "email address is already in use",
                    true
                ) == true ->
                    "Email sudah digunakan akun lain"

                else ->
                    e.message ?: "Gagal memperbarui email"
            }

            Result.failure(
                Exception(message)
            )
        }
    }

    suspend fun updatePassword(
        oldPassword: String,
        newPassword: String
    ): Result<String> {

        if (newPassword.isBlank()) {
            return Result.success(
                "Password tidak diubah"
            )
        }

        if (oldPassword.isBlank()) {
            return Result.failure(
                Exception("Password lama wajib diisi")
            )
        }

        if (newPassword.length < 6) {
            return Result.failure(
                Exception("Password baru minimal 6 karakter")
            )
        }

        return try {
            val user = auth.currentUser
                ?: return Result.failure(
                    Exception("User belum login")
                )

            val email = user.email
                ?: return Result.failure(
                    Exception("Email user tidak ditemukan")
                )

            val credential =
                EmailAuthProvider.getCredential(
                    email,
                    oldPassword
                )

            user.reauthenticate(
                credential
            ).await()

            user.updatePassword(
                newPassword
            ).await()

            Result.success(
                "Password berhasil diperbarui"
            )

        } catch (e: Exception) {

            val message = when {

                e.message?.contains(
                    "password is invalid",
                    true
                ) == true ->
                    "Password lama salah"

                e.message?.contains(
                    "credential is incorrect",
                    true
                ) == true ->
                    "Password lama salah"

                e.message?.contains(
                    "least 6 characters",
                    true
                ) == true ->
                    "Password baru minimal 6 karakter"

                e.message?.contains(
                    "requires recent login",
                    true
                ) == true ->
                    "Silakan login ulang sebelum mengganti password"

                else ->
                    e.message ?: "Gagal memperbarui password"
            }

            Result.failure(
                Exception(message)
            )
        }
    }

    suspend fun updateProfile(
        username: String,
        email: String,
        oldPassword: String,
        newPassword: String,
        photoUrl: String
    ): Result<String> {

        val updateUsernameResult =
            updateUsername(
                username = username
            )

        if (updateUsernameResult.isFailure) {
            return Result.failure(
                Exception(
                    updateUsernameResult.exceptionOrNull()?.message
                        ?: "Gagal memperbarui username"
                )
            )
        }

        val updatePhotoResult =
            updatePhotoUrl(
                photoUrl = photoUrl
            )

        if (updatePhotoResult.isFailure) {
            return Result.failure(
                Exception(
                    updatePhotoResult.exceptionOrNull()?.message
                        ?: "Gagal memperbarui foto profil"
                )
            )
        }

        if (email.trim() != getEmail()) {
            val updateEmailResult =
                updateEmail(
                    email = email
                )

            if (updateEmailResult.isFailure) {
                return Result.failure(
                    Exception(
                        updateEmailResult.exceptionOrNull()?.message
                            ?: "Gagal memperbarui email"
                    )
                )
            }

            if (newPassword.isNotBlank()) {
                val updatePasswordResult =
                    updatePassword(
                        oldPassword = oldPassword,
                        newPassword = newPassword
                    )

                if (updatePasswordResult.isFailure) {
                    return Result.failure(
                        Exception(
                            updatePasswordResult.exceptionOrNull()?.message
                                ?: "Gagal memperbarui password"
                        )
                    )
                }
            }

            return Result.success(
                "Profil berhasil diperbarui. Cek email baru kamu untuk verifikasi perubahan email."
            )
        }

        if (newPassword.isNotBlank()) {
            val updatePasswordResult =
                updatePassword(
                    oldPassword = oldPassword,
                    newPassword = newPassword
                )

            if (updatePasswordResult.isFailure) {
                return Result.failure(
                    Exception(
                        updatePasswordResult.exceptionOrNull()?.message
                            ?: "Gagal memperbarui password"
                    )
                )
            }
        }

        return Result.success(
            "Profil berhasil diperbarui"
        )
    }
}