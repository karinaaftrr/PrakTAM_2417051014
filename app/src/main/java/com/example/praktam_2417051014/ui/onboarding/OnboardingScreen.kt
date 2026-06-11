package com.example.praktam_2417051014.ui.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.praktam_2417051014.R
import com.example.praktam_2417051014.ui.theme.*
import kotlinx.coroutines.delay

@Composable
fun OnboardingScreen(
    onFinish: () -> Unit
) {

    LaunchedEffect(Unit) {
        delay(3000)
        onFinish()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        SoftBlueBackground,
                        AppBackground,
                        CardWhite
                    )
                )
            )
            .padding(horizontal = 24.dp)
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(
                modifier = Modifier.height(140.dp)
            )

            Image(
                painter = painterResource(
                    id = R.drawable.logo_basickuiz
                ),
                contentDescription = "Logo BasicQuizz",
                modifier = Modifier.size(180.dp)
            )

            Spacer(
                modifier = Modifier.height(32.dp)
            )

            Text(
                text = "BelajarYuk",
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryColor
            )

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            Text(
                text = "BasicQuizz Ecosystem",
                fontSize = 16.sp,
                color = TextSecondary
            )

            Spacer(
                modifier = Modifier.weight(1f)
            )

            Text(
                text = "Belajar dengan Tenang",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = PrimaryColor
            )

            Spacer(
                modifier = Modifier.height(28.dp)
            )

            LinearProgressIndicator(
                modifier = Modifier
                    .width(70.dp)
                    .height(6.dp)
                    .clip(
                        RoundedCornerShape(50.dp)
                    ),
                color = PrimaryColor,
                trackColor = BorderSoft
            )

            Spacer(
                modifier = Modifier.height(70.dp)
            )
        }
    }
}