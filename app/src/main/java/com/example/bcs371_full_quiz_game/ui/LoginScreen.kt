package com.example.bcs371_full_quiz_game.ui

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.example.bcs371_full_quiz_game.QuizActivity
import com.example.bcs371_full_quiz_game.ui.theme.getStoredCredentials

@Composable
fun LoginScreen(navController: NavController) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF4FC3F7),  // Sky blue
                        Color(0xFFF06292),  // Pink
                        Color(0xFFFFCA28)   // Yellow-orange
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth(0.9f)        // Set the width to 90% of the Box
                .fillMaxHeight(0.5f)       // Set the height to 80% of the Box
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .padding(16.dp)
        ) {
            Text("Login to Quiz Game", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))

            var email by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }
            var loginError by remember { mutableStateOf<String?>(null) }

            // Login fields
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email Address") },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Display error message if login fails
            if (loginError != null) {
                Text(text = loginError!!, color = Color.Red)
                Spacer(modifier = Modifier.height(8.dp))
            }

            Button(onClick = {
                // Retrieve stored credentials
                val (storedEmail, storedPassword) = getStoredCredentials(context)

                // Display Toast with stored credentials for debugging purposes
                //Toast.makeText(context, "Stored Email: $storedEmail\nStored Password: $storedPassword", Toast.LENGTH_LONG).show()

                if (email == storedEmail && password == storedPassword) {
                    // Successful login
                    Toast.makeText(context, "Login Successful", Toast.LENGTH_LONG).show()
                    context.startActivity(Intent(context, QuizActivity::class.java))
                } else {
                    // Show error if credentials don't match
                    loginError = "Invalid email or password"
                }
            }) {
                Text("Login")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Sign Up link for users without an account
            Text(
                text = "Don't have an account? Sign Up Here!",
                modifier = Modifier.clickable {
                    navController.navigate("register_screen") // Navigate to the register screen
                }
            )
        }
    }
}