package com.example.bcs371_full_quiz_game

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bcs371_full_quiz_game.ui.LoginScreen
import com.example.bcs371_full_quiz_game.ui.RegisterScreen
import com.example.bcs371_full_quiz_game.ui.theme.BCS371_Full_Quiz_GameTheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BCS371_Full_Quiz_GameTheme {
                LoginNavigation() // Handles login and registration screens
            }
        }
    }
}

@Composable
fun LoginNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login_screen") {
        composable("login_screen") {
            LoginScreen(navController = navController)
        }
        composable("register_screen") {
            RegisterScreen(navController = navController)
        }
    }
}
