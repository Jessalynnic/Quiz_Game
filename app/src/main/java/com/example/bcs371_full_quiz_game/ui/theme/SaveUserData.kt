package com.example.bcs371_full_quiz_game.ui.theme

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