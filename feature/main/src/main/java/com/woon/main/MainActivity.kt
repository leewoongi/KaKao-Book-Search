package com.woon.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.woon.kakaobooksearch.ui.theme.KaKaoBookSearchTheme
import com.woon.main.ui.BottomNavigationBar
import com.woon.main.ui.NavigationGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KaKaoBookSearchTheme {
                val navController = rememberNavController()
                val isVisible = true
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize(),
                    containerColor = Color.White,
                    bottomBar = {
                        if (isVisible) {
                            BottomNavigationBar(
                                navController = navController
                            )
                        }
                    }
                ) { innerPadding ->
                    NavigationGraph(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}