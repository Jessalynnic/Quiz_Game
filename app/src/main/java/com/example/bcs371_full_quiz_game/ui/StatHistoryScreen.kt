package com.example.bcs371_full_quiz_game.ui

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import com.example.bcs371_full_quiz_game.ui.data.getQuizStats

@Composable
fun StatHistoryScreen(navController: NavController, context: Context) {
    val quizStats = getQuizStats(context) // Retrieve saved stats

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
            modifier = Modifier
                .fillMaxWidth(0.9f)        // Set the width to 90% of the Box
                .fillMaxHeight(0.8f)       // Set the height to 80% of the Box
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Quiz History", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(16.dp))

            // Display each saved game with score and earnings
            quizStats.forEachIndexed { index, (correctAnswers, earnedAmount) ->
                Text("Game ${index + 1}: Correct - $correctAnswers, Earnings - $earnedAmount", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(8.dp))
            }

            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Back to Rules",
                modifier = Modifier.clickable {
                    navController.navigate("quiz_rules") // Navigate to the register screen
                }
            )
        }
    }
}