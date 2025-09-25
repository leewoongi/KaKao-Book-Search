package com.woon.main.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.woon.core.navigation.LocalNavController
import com.woon.detail.DetailScreen
import com.woon.favorite.FavoriteScreen
import com.woon.home.HomeScreen

@Composable
fun NavigationGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    CompositionLocalProvider(LocalNavController provides navController) {
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = modifier
        ) {
            composable("home") {
                HomeScreen()
            }

            composable("favorite") {
                FavoriteScreen()
            }

            composable("detail") {
                DetailScreen()
            }
        }
    }
}