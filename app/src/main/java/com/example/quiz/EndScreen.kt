package com.example.quiz

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.material3.ButtonDefaults
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

val shape1 = RoundedCornerShape(16.dp)

@Composable
fun EndScreen(score: Int, highscore: Int, onRestart: () -> Unit) {
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
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    ) {
        Text(
            text = ("""Wow! You got $score out of 10!
                |
                |Your score is: $highscore""".trimMargin()).trimIndent(),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = Color.White,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )
        ElevatedButton(
            onClick = { onRestart() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
                .shadow(8.dp, shape1),
            shape = shape,
            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = Color.DarkGray,
                contentColor = Color.White
            )
        ) {
            Text(
                "Restart Quiz",
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EndScreenPreview() {
    QuizTheme {
        EndScreen(7, 100, onRestart = {})
    }
}
