package com.example.gym

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import com.example.gym.theme.GymTheme
import com.example.gym.ui.LauncherScreen
import com.example.gym.ui.navigation.GymNavHost

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            GymTheme {
                GymNavHost()
            }
        }
    }
}