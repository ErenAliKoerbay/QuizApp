package com.example.quiz

import android.R.color.white
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quiz.ui.theme.QuizTheme

@Composable
fun StartScreen(onStartClicked: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(Color.DarkGray, Color.Black),
                    center = Offset.Infinite,
                    radius = 2000f
                )
            )

            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
                text = ("""WELCOME TO THE
                    |
                    |MOVIE-QUIZ
                """.trimMargin()).trimIndent(),
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .shadow(10.dp)
        )
        ElevatedButton(
            onClick = { onStartClicked() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .shadow(8.dp, RoundedCornerShape(16.dp)),
                    colors = androidx.compose.material3.ButtonDefaults.elevatedButtonColors(
                    containerColor = Color.DarkGray,
                    contentColor = Color.White)
        ) {
            Text("Start Quiz",
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun StartScreenPreview() {
    QuizTheme {
        StartScreen(onStartClicked = {})
    }
}
