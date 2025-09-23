package com.woon.main.ui.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home

sealed class BottomNavItem(
    val route: String,
    val title: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
) {
    object Home : BottomNavItem("home", "홈", Icons.Default.Home)
    object Favorites : BottomNavItem("favorites", "즐겨찾기", Icons.Default.Favorite)
}