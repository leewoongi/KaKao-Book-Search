package com.woon.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.woon.core.design.textview.BTextView

@Composable
fun EmptyScreen(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
){
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        BTextView(
            text = "검색 결과가 없습니다.",
            color = Color(0xFF111827),
            textStyle = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        )

        Spacer(modifier = Modifier.height(4.dp))
        Button(
            onClick = { onClick() },
            content = {
                BTextView(
                    text = "재시도",
                    color = Color(0xFF111827),
                    textStyle = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal
                    )
                )
            }
        )
    }
}