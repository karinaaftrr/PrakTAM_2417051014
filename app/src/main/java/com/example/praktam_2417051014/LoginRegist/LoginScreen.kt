package com.example.praktam_2417051014.LoginRegist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.praktam_2417051014.ui.theme.*
import com.example.praktam_2417051014.viewmodel.LoginViewModel

@Composable
fun LoginScreen(navController: NavController) {

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var loading by remember { mutableStateOf(false) }
    var errorText by remember { mutableStateOf("") }

    var passwordVisible by remember { mutableStateOf(false) }

    val viewModel = remember { LoginViewModel() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppBackground)
            .padding(horizontal = 28.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(55.dp))

        Text(
            text = "BasicKuiz",
            style = MaterialTheme.typography.headlineMedium,
            color = PrimaryColor
        )

        Text(
            text = "Asah Otakmu, Taklukkan Setiap Kuis.",
            style = MaterialTheme.typography.bodySmall,
            color = TextLight
        )

        Spacer(modifier = Modifier.height(40.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            Text(
                text = "Daftar",
                style = MaterialTheme.typography.titleMedium,
                color = TextSecondary,
                modifier = Modifier.clickable {
                    navController.navigate("register")
                }
            )

            Text(
                text = "Masuk",
                style = MaterialTheme.typography.titleMedium,
                color = PrimaryColor,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {

            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(5.dp)
                    .background(
                        SecondaryColor.copy(alpha = 0.4f),
                        RoundedCornerShape(20.dp)
                    )
            )

            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(5.dp)
                    .background(
                        PrimaryColor,
                        RoundedCornerShape(20.dp)
                    )
            )
        }

        Spacer(modifier = Modifier.height(45.dp))

        Text(
            text = "Login Pengguna",
            style = MaterialTheme.typography.titleLarge,
            color = TextPrimary
        )

        Spacer(modifier = Modifier.height(35.dp))

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text = "Username",
                style = MaterialTheme.typography.labelLarge,
                color = TextSecondary
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                placeholder = {
                    Text(
                        text = "Masukkan username",
                        color = TextLight
                    )
                },
                singleLine = true,
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = CardWhite,
                    unfocusedContainerColor = CardWhite,
                    focusedBorderColor = PrimaryColor,
                    unfocusedBorderColor = SecondaryColor.copy(alpha = 0.5f),
                    cursorColor = PrimaryColor
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(18.dp))

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {

            Text(
                text = "Password",
                style = MaterialTheme.typography.labelLarge,
                color = TextSecondary
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = {
                    Text(
                        text = "Masukkan Password",
                        color = TextLight
                    )
                },
                singleLine = true,
                visualTransformation =
                    if (passwordVisible) {
                        VisualTransformation.None
                    } else {
                        PasswordVisualTransformation()
                    },
                trailingIcon = {
                    IconButton(
                        onClick = {
                            passwordVisible = !passwordVisible
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.VisibilityOff,
                            contentDescription = null,
                            tint = TextSecondary
                        )
                    }
                },
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = CardWhite,
                    unfocusedContainerColor = CardWhite,
                    focusedBorderColor = PrimaryColor,
                    unfocusedBorderColor = SecondaryColor.copy(alpha = 0.5f),
                    cursorColor = PrimaryColor
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Lupa Password?",
            color = FavoriteRed,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier
                .align(Alignment.End)
                .clickable { }
        )

        Spacer(modifier = Modifier.height(28.dp))

        if (errorText.isNotEmpty()) {

            Text(
                text = errorText,
                color = FavoriteRed,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(12.dp))
        }

        Button(
            onClick = {

                loading = true
                errorText = ""

                viewModel.login(

                    username = username,
                    password = password,

                    onSuccess = {

                        loading = false

                        navController.navigate("dashboard") {
                            popUpTo("login") {
                                inclusive = true
                            }
                        }
                    },

                    onError = {

                        loading = false
                        errorText = it
                    }
                )
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = PrimaryColor
            ),
            shape = RoundedCornerShape(18.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp)
                .shadow(
                    elevation = 6.dp,
                    shape = RoundedCornerShape(18.dp)
                )
        ) {

            if (loading) {

                CircularProgressIndicator(
                    modifier = Modifier.size(22.dp),
                    color = Color.White,
                    strokeWidth = 2.dp
                )

            } else {

                Text(
                    text = "Masuk",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White
                )
            }
        }
    }
}