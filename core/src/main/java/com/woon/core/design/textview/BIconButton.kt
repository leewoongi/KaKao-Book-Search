package com.woon.core.design.textview

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun BIconButton(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    iconColor: Color = Color.Unspecified,
    contentDescription: String = "",
    onClick: () -> Unit,
){
    IconButton(
        onClick = onClick,
        modifier = modifier
    ){
        Icon(
            modifier = Modifier.size(16.dp),
            imageVector = icon,
            contentDescription = contentDescription,
            tint =  iconColor
        )
    }
}