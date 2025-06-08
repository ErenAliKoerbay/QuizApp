package com.example.quiz

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.content.Context
import android.media.MediaPlayer
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext

val shape = RoundedCornerShape(12.dp)
val defaultColor = Color(0xFFE0E0E0)

fun playAnswerSound(context: Context, correct: Boolean) {
    val soundRes = if (correct) R.raw.right else R.raw.fail
    val mediaPlayer = MediaPlayer.create(context, soundRes)
    mediaPlayer.start()
    mediaPlayer.setOnCompletionListener {
        it.release()
    }
}

@Composable
fun VisualQuizShow(onQuizFinished: (Int, Int) -> Unit) {
    val context = LocalContext.current
    // for the endscreen
    var correctAnsweredQuestions by remember { mutableIntStateOf(0) }

    // states to later on change the color when user clicks on answer
    val backgroundColors = remember { mutableStateListOf(defaultColor, defaultColor, defaultColor) }

    // current questionIndex to display questions one by one
    var currentQuestionIndex by remember { mutableIntStateOf(0) }

    // current question
    val currentQuestion = questions[currentQuestionIndex]

    // um next question button zu verstecken
    var showButton by remember { mutableStateOf(false) }

    var quizFinished by remember { mutableStateOf(false) }

    /* possible answers, in remember so the
    answers only shuffle once every question */
    val answers = remember(currentQuestionIndex) {
        listOf(
            currentQuestion.answer1,
            currentQuestion.answer2,
            currentQuestion.rightAnswer,
        ).shuffled()
    }

    var score by remember { mutableIntStateOf(0) }

    val defaultBackgroundColor = Color.DarkGray

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(Color.DarkGray, Color.Black),
                    center = Offset.Infinite,
                    radius = 2000f
                )
            )
            .padding(16.dp)
    ) {
        // Score oben links, nicht zentriert
        Text(
            text = "Current Score: $score",
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
                .fillMaxWidth(),
            color = Color(0xFF64B5F6),
            textAlign = TextAlign.Start
        )

        // Rest zentriert (horizontal + vertikal)
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = currentQuestion.question,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                color = Color.White,
                fontSize = 32.sp,
                textAlign = TextAlign.Center,
                lineHeight = 40.sp,
            )
            answers.forEachIndexed { i, item ->
                Text(
                    text = item,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clip(shape)
                        .shadow(8.dp, shape)
                        .background(backgroundColors[i].takeIf { it != defaultColor } ?: defaultBackgroundColor)
                        .clickable {
                            // only accept answer once and update colors + score
                            if (!showButton && currentQuestionIndex <= 8) {
                                showButton = true
                                backgroundColors[i] = if (item == currentQuestion.rightAnswer) {
                                    score += 100
                                    playAnswerSound(context, true)
                                    correctAnsweredQuestions++
                                    Color(0xFF388E3C)
                                } else {
                                    playAnswerSound(context, false)
                                    Color(0xFFD32F2F)
                                }
                            } else if (currentQuestionIndex == 9 && !quizFinished) {
                                quizFinished = true
                                backgroundColors[i] = if (item == currentQuestion.rightAnswer) {
                                    score += 100
                                    playAnswerSound(context, true)
                                    correctAnsweredQuestions++
                                    Color(0xFF388E3C)
                                } else {
                                    playAnswerSound(context, false)
                                    Color(0xFFD32F2F)
                                }
                            }
                        }
                        .padding(16.dp),
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center
                )
            }
            // show next question button only if answered
            if (showButton) {
                ElevatedButton(
                    onClick = {
                        showButton = false
                        currentQuestionIndex++
                        // reset background colors for next question
                        for (i in backgroundColors.indices) {
                            backgroundColors[i] = defaultColor
                        }
                    },
                    modifier = Modifier
                        .padding(top = 24.dp)
                        .fillMaxWidth()
                        .shadow(8.dp, RoundedCornerShape(16.dp)),
                    shape = RoundedCornerShape(16.dp),
                    colors = androidx.compose.material3.ButtonDefaults.elevatedButtonColors(
                        containerColor = Color.DarkGray,
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        "Next Question",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            }
            // show finish quiz button at the end
            if (quizFinished) {
                ElevatedButton(
                    onClick = {
                        quizFinished = false
                        onQuizFinished(correctAnsweredQuestions, score)
                    },
                    modifier = Modifier
                        .padding(top = 24.dp)
                        .fillMaxWidth()
                        .shadow(8.dp, RoundedCornerShape(16.dp)),
                    shape = RoundedCornerShape(16.dp),
                    colors = androidx.compose.material3.ButtonDefaults.elevatedButtonColors(
                        containerColor = Color.DarkGray,
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        "Finish Quiz",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VisualQuizShowPreview() {
    VisualQuizShow { correct, score -> }
}
