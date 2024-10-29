package com.example.bcs371_full_quiz_game.ui.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class QuizViewModel : ViewModel() {
    // Mutable state for tracking earned amount
    var earnedAmount by mutableStateOf(0)
        private set
    // Mutable state to track the number of correct answers
    var correctAnswers by mutableStateOf(0)
        private set

    // List of questions for the quiz
    val questions: List<Question> = listOf(
        Question(
            questionText = "What is the largest planet in our solar system?",
            options = listOf("Earth", "Jupiter", "Saturn", "Mars"),
            correctAnswer = listOf("Jupiter"),
            questionType = QuestionType.RADIO
        ),
        Question(
            questionText = "Which of the following are prime numbers? (Select all that apply)",
            options = listOf("2", "4", "5", "6"),
            correctAnswer = listOf("2", "5"),
            questionType = QuestionType.CHECKBOX
        ),
        Question(
            questionText = "Which layer of the Earth is the hottest?",
            options = listOf("Crust", "Mantle", "Outer Core", "Inner Core"),
            correctAnswer = listOf("Inner Core"),
            questionType = QuestionType.DROPDOWN
        ),
        Question(
            questionText = "How many planets are in our solar system?",
            correctAnswer = listOf("8"),
            questionType = QuestionType.SLIDER,
            sliderRange = 0f..10f
        ),
        Question(
            questionText = "True or False: The Earth revolves around the Sun.",
            options = listOf("True", "False"),
            correctAnswer = listOf("True"),
            questionType = QuestionType.TOGGLE
        ),
        Question(
            questionText = "What is the boiling point of water in degrees Celsius?",
            correctAnswer = listOf("100"),
            questionType = QuestionType.SLIDER,
            sliderRange = 0f..150f
        ),
        Question(
            questionText = "Which of the following are renewable energy sources? (Select all that apply)",
            options = listOf("Solar", "Coal", "Wind", "Natural Gas"),
            correctAnswer = listOf("Solar", "Wind"),
            questionType = QuestionType.CHECKBOX
        )
    )

    // Mutable state to track the index of the current question
    var currentQuestion by mutableStateOf(0)
        private set

    // Mutable state to track the currently selected answer for single-choice questions
    var selectedAnswer by mutableStateOf("")
        private set

    // Mutable state to track selected checkboxes for multiple-choice questions
    var selectedCheckboxes by mutableStateOf(mutableSetOf<String>())

    // Function to select an answer for radio, dropdown, slider, or toggle questions
    fun selectAnswer(answer: String) {
        selectedAnswer = answer
    }

    // Function to toggle the selection of an option in a checkbox question
    fun toggleCheckbox(option: String) {
        selectedCheckboxes = selectedCheckboxes.toMutableSet().also { set ->
            if (set.contains(option)) {
                // If the option is already selected, remove it
                set.remove(option)
            } else {
                // Otherwise, add the option
                set.add(option)
            }
        }
    }

    // Function to add earnings when the user answers correctly
    fun addEarnings(amount: Int) {
        earnedAmount += amount
    }

    // Function to increment the number of correct answers
    fun incrementCorrectAnswers() {
        correctAnswers += 1
    }

    // Function to move to the next question
    fun moveToNextQuestion() {
        if (currentQuestion < questions.size - 1) {
            // Increment the current question index
            currentQuestion++
            // Clear selected checkboxes when moving to the next question
            selectedCheckboxes.clear()
        }
    }

    // Function to check if the current question is the last one
    fun isLastQuestion(): Boolean {
        // Returns true if the current question is the last one
        return currentQuestion == questions.size - 1
    }

    // Function to get the current question based on the current question index
    fun getCurrentQuestion(): Question = questions[currentQuestion]

    // Reset the state to start the quiz again
    fun resetQuiz() {
        earnedAmount = 0
        correctAnswers = 0
        currentQuestion = 0
        selectedAnswer = ""
        selectedCheckboxes.clear()
    }
}