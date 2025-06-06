package com.example.quiz

data class Question(
    var index: Int,
    var question: String,
    var answer1: String,
    var answer2: String,
    var rightAnswer: String,
)

var questions = listOf(
    Question(
        index = 0,
        question = "Was geht?",
        answer1 = "nichts bei dir?",
        answer2 = "alles gut!",
        rightAnswer = "was machen sachen?",
    ),
    Question(
        index = 1,
        question = "was bedeutet hund?",
        answer1 = "deine Mutter!",
        answer2 = "dein Vater!!",
        rightAnswer = "deine Schwester!!",
    ),
    Question(
        index = 2,
        question = "was bedeutet katze?",
        answer1 = "deine bruder!",
        answer2 = "dein tonne!!",
        rightAnswer = "deine lol!!",
    ),
    Question(
        index = 3,
        question = "was bedeutet katze?",
        answer1 = "deine bruder!",
        answer2 = "dein tonne!!",
        rightAnswer = "deine lol!!",
    ),
    Question(
        index = 4,
        question = "was bedeutet katze?",
        answer1 = "deine bruder!",
        answer2 = "dein tonne!!",
        rightAnswer = "deine lol!!",
    ),
    Question(
        index = 5,
        question = "was bedeutet katze?",
        answer1 = "deine bruder!",
        answer2 = "dein tonne!!",
        rightAnswer = "deine lol!!",
    ),
    Question(
        index = 6,
        question = "was bedeutet katze?",
        answer1 = "deine bruder!",
        answer2 = "dein tonne!!",
        rightAnswer = "deine lol!!",
    ),
    Question(
        index = 7,
        question = "was bedeutet katze?",
        answer1 = "deine bruder!",
        answer2 = "dein tonne!!",
        rightAnswer = "deine lol!!",
    ),
    Question(
        index = 8,
        question = "was bedeutet katze?",
        answer1 = "deine bruder!",
        answer2 = "dein tonne!!",
        rightAnswer = "deine lol!!",
    ),
    Question(
        index = 9,
        question = "was bedeutet katze?",
        answer1 = "deine bruder!",
        answer2 = "dein tonne!!",
        rightAnswer = "deine lol!!",
    ),
)