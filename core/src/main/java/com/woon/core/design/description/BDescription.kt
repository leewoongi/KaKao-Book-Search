package com.woon.core.design.description

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.woon.core.design.textview.BTextView

@Composable
fun BDescription(
    modifier: Modifier = Modifier,
    index: String,
    description: String
){
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BTextView(
            text = index,
            textStyle = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            ),
            maxLines = 1,
            color = Color.Gray
        )

        BTextView(
            text = description,
            maxLines = 1,
            textStyle = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            ),
            color = Color.Black
        )
    }
}