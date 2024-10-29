package com.example.bcs371_full_quiz_game.ui.data

// Data class representing a single question in the quiz
data class Question(
    // The text of the question
    val questionText: String,
    // List of possible answer options
    val options: List<String> = emptyList(),
    // The correct answer(s) for the question (can be multiple for Checkbox questions)
    val correctAnswer: List<String> = emptyList(),
    // Type of question (Radio, Checkbox, Slider)
    val questionType: QuestionType,
    // Optional range for Slider questions
    val sliderRange: ClosedFloatingPointRange<Float>? = null
)

// Enum class defining the different types of questions in the quiz
enum class QuestionType {
    RADIO, CHECKBOX, SLIDER, DROPDOWN, TOGGLE
}