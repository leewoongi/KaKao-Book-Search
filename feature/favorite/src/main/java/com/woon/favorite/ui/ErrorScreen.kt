package com.woon.favorite.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import com.woon.domain.book.exception.BookException

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    error: Throwable,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        BTextView(
            text = when(error) {
                is BookException.Network -> "네트워크 오류가 발생했습니다."
                else -> "알 수 없는 오류가 발생했습니다."
            },
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