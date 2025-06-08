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
        question = "Wie heißen die Filmadaptionen des gleichnamigen Buches von Louisa May Alcott aus den Jahren 1868 und 1869?",
        answer1 = "Portrait of a Lady on Fire",
        answer2 = "Frankenstein",
        rightAnswer = "Little Women",
    ),
    Question(
        index = 1,
        question = "Wie viele Oscars gewann der Film Roma (2018) bei den 91. Academy Awards?",
        answer1 = "4",
        answer2 = "1",
        rightAnswer = "3",
    ),
    Question(
        index = 2,
        question = "In welchem Land spielt Portrait of a Lady on Fire hauptsächlich?",
        answer1 = "Italien",
        answer2 = "Spanien",
        rightAnswer = "Frankreich",
    ),
    Question(
        index = 3,
        question = "Welche Hauptrolle spielt Hailee Steinfeld in The Edge of Seventeen (2016)?",
        answer1 = "Margaret",
        answer2 = "Maggie",
        rightAnswer = "Nadine",
    ),
    Question(
        index = 4,
        question = "Wer spielte die Hauptrolle in Eternal Sunshine of the Spotless Mind (2004)?",
        answer1 = "Joseph Gordon-Levitt",
        answer2 = "Brad Pitt",
        rightAnswer = "Jim Carrey",
    ),
    Question(
        index = 5,
        question = "Welcher Regisseur schuf Pans Labyrinth (2006)?",
        answer1 = "Alejandro González Iñárritu",
        answer2 = "Alfonso Cuarón",
        rightAnswer = "Guillermo del Toro",
    ),
    Question(
        index = 6,
        question = "Wer spielte die weibliche Hauptrolle in Lady Bird (2017)?",
        answer1 = "Emma Watson",
        answer2 = "Florence Pugh",
        rightAnswer = "Saoirse Ronan",
    ),
    Question(
        index = 7,
        question = "Was läuft in der Schlussszene von Portrait of a Lady on Fire, während Marianne Héloïse beobachtet?",
        answer1 = "Ein Streichquartett von Bach",
        answer2 = "Ein Lied aus ihrer Kindheit",
        rightAnswer = "Vivaldis Sommer",
    ),
    Question(
        index = 8,
        question = "In welcher Stadt spielt Debi tirar mais fotos (2023) von Ana Maria Gomes hauptsächlich?",
        answer1 = "Porto",
        answer2 = "Lisboa",
        rightAnswer = "Amadora",
    ),
    Question(
        index = 9,
        question = "Wie heißt der fliegende Inselstaat im Studio-Ghibli-Film Das Schloss im Himmel (1986)?",
        answer1 = "Ghiblara",
        answer2 = "Arion",
        rightAnswer = "Laputa",
    ),
)