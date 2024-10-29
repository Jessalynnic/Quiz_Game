package com.example.bcs371_full_quiz_game.ui.data

import android.content.Context
import android.content.SharedPreferences

// Save user data in SharedPreferences
fun saveUserData(context: Context, firstName: String, lastName: String, dob: String, email: String, password: String) {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)
    with(sharedPreferences.edit()) {
        putString("firstName", firstName)
        putString("lastName", lastName)
        putString("dob", dob)
        putString("email", email)
        putString("password", password)
        apply() // Saves data asynchronously
    }
}

// Retrieve stored user email and password from SharedPreferences
fun getStoredCredentials(context: Context): Pair<String?, String?> {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)
    val storedEmail = sharedPreferences.getString("email", null)
    val storedPassword = sharedPreferences.getString("password", null)
    return Pair(storedEmail, storedPassword)
}

// Save quiz stats to SharedPreferences
fun saveQuizStats(context: Context, correctAnswers: Int, earnedAmount: Int) {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("QuizStats", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()

    // Increment the count of saved games
    val gameCount = sharedPreferences.getInt("gameCount", 0) + 1
    editor.putInt("gameCount", gameCount)

    // Store the correct answers and earnings for each game
    editor.putInt("correctAnswers_$gameCount", correctAnswers)
    editor.putInt("earnedAmount_$gameCount", earnedAmount)

    editor.apply() // Save changes asynchronously
}

// Retrieve all quiz stats from SharedPreferences
fun getQuizStats(context: Context): List<Pair<Int, Int>> {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("QuizStats", Context.MODE_PRIVATE)
    val gameCount = sharedPreferences.getInt("gameCount", 0)

    val statsList = mutableListOf<Pair<Int, Int>>()
    for (i in 1..gameCount) {
        val correctAnswers = sharedPreferences.getInt("correctAnswers_$i", 0)
        val earnedAmount = sharedPreferences.getInt("earnedAmount_$i", 0)
        statsList.add(Pair(correctAnswers, earnedAmount))
    }
    return statsList
}