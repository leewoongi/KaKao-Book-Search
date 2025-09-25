package com.woon.detail.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.woon.core.design.description.BDescription
import com.woon.core.design.textview.BTextView
import com.woon.detail.model.BookUiModel

@Composable
fun FooterScreen(
    modifier: Modifier = Modifier,
    item: BookUiModel
){
    Column(
        modifier = modifier
    ){
        BTextView(
            text = "책소개",
            textStyle = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            ),
            maxLines = 3,
            color = Color.Black,
        )

        Spacer(modifier = Modifier.height(2.dp))

        BTextView(
            text = item.contents,
            textStyle = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal
            ),
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            color = Color(0xFF6B7280),
        )

        Spacer(modifier = Modifier.height(24.dp))

        BTextView(
            text = "상세 정보",
            textStyle = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            ),
            maxLines = 3,
            color = Color.Black,
        )

        Spacer(modifier = Modifier.height(2.dp))

        BDescription(
            modifier = Modifier.fillMaxWidth(),
            index = "ISBN",
            description = item.isbn
        )

        Spacer(modifier = Modifier.height(2.dp))

        BDescription(
            modifier = Modifier.fillMaxWidth(),
            index = "출판사",
            description = item.publisher
        )

        Spacer(modifier = Modifier.height(2.dp))

        BDescription(
            modifier = Modifier.fillMaxWidth(),
            index = "출판일",
            description = item.time
        )

        Spacer(modifier = Modifier.height(2.dp))
    }
}