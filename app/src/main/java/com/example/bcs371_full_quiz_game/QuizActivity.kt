package com.example.bcs371_full_quiz_game

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bcs371_full_quiz_game.ui.QuestionScreen
import com.example.bcs371_full_quiz_game.ui.theme.BCS371_Full_Quiz_GameTheme
import com.example.bcs371_full_quiz_game.ui.QuizRulesScreen
import com.example.bcs371_full_quiz_game.ui.StatHistoryScreen
import com.example.bcs371_full_quiz_game.ui.StatScreen
import com.example.bcs371_full_quiz_game.ui.data.QuizViewModel

class QuizActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BCS371_Full_Quiz_GameTheme {
                QuizNavigation()
            }
        }
    }
}

@Composable
fun QuizNavigation() {
    val navController = rememberNavController()
    val quizViewModel: QuizViewModel = viewModel()
    NavHost(navController = navController, startDestination = "quiz_rules") {
        // Question rules route
        composable("quiz_rules") {
            QuizRulesScreen(navController)
        }
        // Question screen route
        composable("question_screen") {
            QuestionScreen(navController, viewModel = quizViewModel)
        }
        // Stats screen route
        composable("stat_screen"
        ) {
            StatScreen(navController, viewModel = quizViewModel)
        }
        // Stats screen route
        composable("stat_history"
        ) {
            val context = LocalContext.current // Get context within the composable scope
            StatHistoryScreen(navController, context = context)
        }
    }
}