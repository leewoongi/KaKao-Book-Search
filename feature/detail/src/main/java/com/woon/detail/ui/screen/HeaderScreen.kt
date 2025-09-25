package com.woon.detail.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.woon.core.design.button.BIconButton
import com.woon.detail.model.BookUiModel

@Composable
fun HeaderScreen(
    modifier: Modifier = Modifier,
    item: BookUiModel,
    onBack: () -> Unit,
    onClickFavorite: () -> Unit,
){
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BIconButton(
            icon = Icons.AutoMirrored.Filled.ArrowBack,
            onClick = { onBack() }
        )

        BIconButton(
            modifier = Modifier
                .padding(4.dp)
                .background(
                    color = Color.Black.copy(alpha = 0.6f),
                    shape = CircleShape
                )
                .border(
                    width = 1.dp,
                    color = Color.White.copy(alpha = 0.3f),
                    shape = CircleShape
                )
                .size(24.dp),
                icon = if (item.isFavorite) Icons.Filled.Favorite else Icons.Default.FavoriteBorder,
                iconColor = if (item.isFavorite) Color(0xFFE50914) else Color.White,
            onClick = { onClickFavorite() }
        )
    }
}