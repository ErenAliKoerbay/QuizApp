package com.example.quiz

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


val shape = RoundedCornerShape(12.dp)
val defaultColor = Color(0xFFE0E0E0)

@Composable
fun VisualQuizShow(onQuizFinished: (Int, Int) -> Unit, modifier: Modifier = Modifier) {

    // for the endscreen
    var correctAnsweredQuestions by remember { mutableIntStateOf(0) }

    // states to later on change the color when user clicks on answer
    val backgroundColors =
        remember { mutableStateListOf<Color>(defaultColor, defaultColor, defaultColor) }

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

    Column(
        modifier =
            Modifier
                .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(3.dp)
    )
    {
        Text(
            text = "Current Score: ${score}",
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Text(
            text = currentQuestion.question,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        answers.forEachIndexed { i, item ->
            Text(
                text = item,
                modifier = Modifier
                    .clip(shape)
                    .clickable {
                        if (!showButton && currentQuestionIndex <= 8) {
                            showButton = true
                            backgroundColors[i] =
                                    // item being the clicked button
                                if (item == currentQuestion.rightAnswer) {
                                    score += 100
                                    correctAnsweredQuestions++
                                    Color(0xFFA5D6A7)
                                } else {
                                    Color(0xFFEF9A9A)
                                }
                        } else if (currentQuestionIndex in 9..9) {
                            quizFinished = true
                            backgroundColors[i] =
                                if (item == currentQuestion.rightAnswer) {
                                    score += 100
                                    correctAnsweredQuestions++
                                    Color(0xFFA5D6A7)
                                } else {
                                    Color(0xFFEF9A9A)
                                }
                        }
                    }
                    .background(backgroundColors[i])
                    .padding(16.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
        // next question and button colors back to gray
        if (showButton) {
            ElevatedButton(
                onClick = {
                    showButton = false
                    currentQuestionIndex++
                    //loop for every backgroundColor
                    for (i in backgroundColors.indices) {
                        backgroundColors[i] = defaultColor
                    }
                },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
            ) {
                Text("Next Question")
            }
        }
        if (quizFinished) {
            ElevatedButton(
                onClick = {
                    quizFinished = false
                    onQuizFinished(correctAnsweredQuestions, score)
                },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
            ) {
                Text("Finish Quiz")
            }
        }
    }
    fun getAnswers(): Int {
        return correctAnsweredQuestions
    }
}