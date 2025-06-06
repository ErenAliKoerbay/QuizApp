package com.example.quiz

import EndScreen
import StartScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.quiz.ui.theme.QuizTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuizTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CurrentScreenMethod(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

/* we're using an enum to determine which screen is currently supposed to be active
by using the three different states of the quiz (start, quiz, end) and implementing them
into methods within the classes. Each time the method is triggered, the current state changes. We
are also saving the finalScore of right answered questions for the endscreen and the highscore*/
@Composable
fun CurrentScreenMethod(modifier: Modifier) {
    var currentScreen by remember { mutableStateOf(QuizScreen.START) }
    var finalScore by remember { mutableIntStateOf(0) }
    var highScore by remember { mutableIntStateOf(0) }
    when (currentScreen) {
        QuizScreen.START -> StartScreen(onStartClicked = { currentScreen = QuizScreen.QUIZ })
        QuizScreen.QUIZ -> VisualQuizShow(onQuizFinished = { score, highscoreFinal ->
            finalScore = score
            highScore = highscoreFinal
            currentScreen = QuizScreen.END
        })

        QuizScreen.END -> EndScreen(
            score = finalScore,
            highscore = highScore,
            onRestart = { currentScreen = QuizScreen.START })
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun GreetingPreview() {
    QuizTheme {
        CurrentScreenMethod(modifier = Modifier)
    }
}