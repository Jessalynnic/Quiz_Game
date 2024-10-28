package com.example.bcs371_full_quiz_game.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bcs371_full_quiz_game.ui.theme.getStoredCredentials
import com.example.bcs371_full_quiz_game.ui.theme.saveUserData

@Composable
fun RegisterScreen(navController: NavController) {
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
            )
            .imePadding(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth(0.9f)        // Set the width to 90% of the Box
                .fillMaxHeight(0.8f)       // Set the height to 80% of the Box
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
        ) {
            Text("Register", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))

            // Input states
            var firstName by remember { mutableStateOf("") }
            var lastName by remember { mutableStateOf("") }
            var dob by remember { mutableStateOf("") }
            var email by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }
            var confirmPass by remember { mutableStateOf("") }

            // Error states
            var firstNameError by remember { mutableStateOf<String?>(null) }
            var lastNameError by remember { mutableStateOf<String?>(null) }
            var dobError by remember { mutableStateOf<String?>(null) }
            var emailError by remember { mutableStateOf<String?>(null) }
            var passwordError by remember { mutableStateOf<String?>(null) }
            var confirmPassError by remember { mutableStateOf<String?>(null) }

            // First Name field with length validation
            OutlinedTextField(
                value = firstName,
                onValueChange = {
                    firstName = it
                    // Check if firstName is between 3 and 30 characters
                    firstNameError = when {
                        firstName.length < 3 -> "First name must be at least 3 characters"
                        firstName.length > 30 -> "First name cannot exceed 30 characters"
                        else -> null
                    }
                },
                label = { Text("First Name") },
                isError = firstNameError != null,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
            )
            if (firstNameError != null) Text(firstNameError!!, color = Color.Red)

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = lastName,
                onValueChange = {
                    lastName = it
                    lastNameError = when {
                        lastName.length < 3 -> "Last name must be at least 3 characters"
                        lastName.length > 30 -> "Last name cannot exceed 30 characters"
                        else -> null
                    }
                },
                label = { Text("Last Name") },
                isError = lastNameError != null,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
            )
            if (lastNameError != null) Text(lastNameError!!, color = Color.Red)

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = dob,
                onValueChange = { input ->
                    val cleanedInput = input.filter { it.isDigit() }
                    dob = if (cleanedInput.length <= 4) {
                        cleanedInput
                    } else if (cleanedInput.length <= 6) {
                        cleanedInput.substring(0, 4) + "-" + cleanedInput.substring(4)
                    } else {
                        cleanedInput.substring(0, 4) + "-" + cleanedInput.substring(4, 6) + "-" + cleanedInput.substring(6)
                    }
                },
                label = { Text("Date of Birth") },
                placeholder = { Text("YYYY-MM-DD") },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                    emailError = if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) "Invalid email format" else null
                },
                label = { Text("Email Address") },
                isError = emailError != null,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
            )
            if (emailError != null) Text(emailError!!, color = Color.Red)

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                    passwordError = if (password.length < 6) "Password must be at least 6 characters" else null
                },
                label = { Text("Password") },
                isError = passwordError != null,
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
            )
            if (passwordError != null) Text(passwordError!!, color = Color.Red)

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = confirmPass,
                onValueChange = {
                    confirmPass = it
                    confirmPassError = if (confirmPass != password) "Passwords do not match" else null
                 },
                label = { Text("Confirm Password") },
                isError = confirmPassError != null,
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
            )
            if (confirmPassError != null) Text(confirmPassError!!, color = Color.Red)

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                // Validate the fields first
                if (firstNameError == null && lastNameError == null && dobError == null && emailError == null && passwordError == null && confirmPassError == null) {
                    // Call the function to save user data
                    saveUserData(
                        context = context,
                        firstName = firstName,
                        lastName = lastName,
                        dob = dob,
                        email = email,
                        password = password
                    )

                    Toast.makeText(context, "Registration Successful", Toast.LENGTH_LONG).show()

                    // Navigate to the next screen (e.g., Quiz screen)
                    navController.navigate("login_screen")
                } else {
                    // Show a message if there are validation errors
                    Toast.makeText(context, "Please fix the errors before submitting", Toast.LENGTH_LONG).show()
                }
            }) {
                Text("Register")
            }

        }
    }
}
