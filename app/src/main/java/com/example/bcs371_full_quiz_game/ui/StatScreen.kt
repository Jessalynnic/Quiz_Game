package com.example.bcs371_full_quiz_game.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bcs371_full_quiz_game.ui.data.QuizViewModel

@Composable
fun StatScreen(navController: NavController, viewModel: QuizViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            // Enable vertical scrolling
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // "Game over!" Text
        Text(
            text = "Game over!",
            fontSize = 30.sp,
            modifier = Modifier.padding(bottom = 20.dp)
        )
        // "Here are your statistics" Text
        Text(
            text = "Here are your statistics",
            fontSize = 30.sp,
            modifier = Modifier.padding(bottom = 20.dp)
        )
        // "Amount Correct" Text
        Text(
            text = "Amount Correct",
            fontSize = 23.sp,
            modifier = Modifier.padding(bottom = 20.dp)
        )
        // Display the number of correct answers, with a color applied
        Text(
            // Show the correct answers as "X/7"
            text = "${viewModel.correctAnswers}/7",
            fontSize = 23.sp,
            color = Color(0xFF258172),
            modifier = Modifier.padding(bottom = 20.dp)
        )
        // "Total Earnings" Text
        Text(
            text = "Total Earnings",
            fontSize = 23.sp,
            modifier = Modifier.padding(bottom = 20.dp)
        )
        // Display the total earnings, with a color applied
        Text(
            // Display the total earnings amount
            text = "$" + viewModel.earnedAmount,
            fontSize = 23.sp,
            color = Color(0xFF258172),
            modifier = Modifier.padding(bottom = 20.dp)
        )

        Row (
            verticalAlignment = Alignment.CenterVertically
        )
        {
            // Button to play again
            Button(
                onClick = {
                    // Reset the quiz state when the button is clicked
                    viewModel.resetQuiz()
                    // Navigate back to the first question screen
                    navController.navigate("question_screen")
                },
                modifier = Modifier
                    .width(150.dp)
            ) {
                Text(
                    // Button label text
                    text = "Play Again",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            // Button to exit quiz
            Button(
                onClick = {
                    // Reset the quiz state when the button is clicked
                    viewModel.resetQuiz()
                    // Navigate back to the first question screen
                    navController.navigate("question_screen")
                },
                modifier = Modifier
                    .width(150.dp)

            ) {
                Text(
                    // Button label text
                    text = "Exit Quiz",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                )
            }
        }
    }
}