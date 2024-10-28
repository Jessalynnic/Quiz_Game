package com.example.bcs371_full_quiz_game.ui

import android.content.Intent
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bcs371_full_quiz_game.LoginActivity
import com.example.bcs371_full_quiz_game.R
import kotlinx.coroutines.delay

@Composable
fun Navigation() {
    val navController= rememberNavController()
    NavHost(navController = navController, startDestination = "splash_screen"  ){
        composable("splash_screen"
        ) {
            SplashScreen(navController)

        }
    }

}

@Composable
fun SplashScreen(navController: NavController){
    val scale= remember {
        Animatable(0f, 1f)
    }
    val context = LocalContext.current
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 1.5f,
            animationSpec = tween(durationMillis = 1000,0, easing = {
                OvershootInterpolator(2f).getInterpolation(it)
            }
            ))
        delay(3000)

        context.startActivity(Intent(context, LoginActivity::class.java))
    }

    Box (contentAlignment = Alignment.Center){
        Image(
            painter = painterResource(id = R.drawable.app_icon_5thgrader_trans),
            contentDescription ="",
            modifier = Modifier
                // Set a fixed size for the image
                .size(300.dp)
                // Apply the scaling animation
                .scale(scale.value)
        )
    }
}
