package com.example.bcs371_full_quiz_game.ui

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun QuizRulesScreen(navController: NavController) {
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
                .fillMaxHeight(0.8f)       // Set the height to 80% of the Box
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .padding(16.dp)
        ) {
            Text(
                text = "Quiz Rules",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(
                text = """
                1. The quiz contains 7 questions. Each question may be multiple-choice or multiple-answer.
                
                2. Select your answer and press "Confirm" to lock it in. Once confirmed, you will be directed to the next question.
                
                3. A hundred dollars is awarded for each correct answer. For multiple-answer questions, partial points are given based on correct choices.
                
                4. At the end of the quiz, you'll see your final score.
                
                6. You can retake the quiz to try for a better score if you'd like!
                """.trimIndent(),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(8.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { navController.navigate("question_screen") },
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.primary)
            ) {
                Text(
                    text = "Start Quiz",
                    color = Color.White,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "View Stats History",
                modifier = Modifier.clickable {
                    navController.navigate("stat_history") // Navigate to the register screen
                }
            )
        }
    }
}