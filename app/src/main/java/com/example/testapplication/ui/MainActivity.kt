package com.example.testapplication.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.testapplication.ui.navigation.AppNavigation
import com.example.testapplication.ui.screen.UserViewModel
import com.example.testapplication.ui.theme.TestApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestApplicationTheme {
                Scaffold(
                    modifier = Modifier.Companion.fillMaxSize()) { innerPadding ->
                    AppNavigation(
                        userViewModel,
                        modifier = Modifier.Companion.padding(innerPadding)
                    )
                }
            }
        }
    }
}