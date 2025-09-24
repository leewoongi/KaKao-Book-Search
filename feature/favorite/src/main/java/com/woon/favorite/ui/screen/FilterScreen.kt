package com.woon.favorite.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.woon.core.design.textview.BTextView
import com.woon.favorite.model.SearchFilterStatus

@Composable
fun FilterScreen(
    modifier : Modifier = Modifier,
    onClick: (SearchFilterStatus) -> Unit = {}
){
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ){
        BTextView(
            text = "오름차순",
            color = Color(0xFF111827),
            textStyle = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.clickable(
                onClick = { onClick(SearchFilterStatus.Ascending) }
            )
        )

        Spacer(modifier = Modifier.width(8.dp))

        BTextView(
            text = "내림차순",
            color = Color(0xFF111827),
            textStyle = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.clickable(
                onClick = { onClick(SearchFilterStatus.Descending) }
            )
        )

        Spacer(modifier = Modifier.width(8.dp))

        BTextView(
            text = "가격순",
            color = Color(0xFF111827),
            textStyle = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.clickable(
                onClick = { onClick(SearchFilterStatus.Price) }
            )
        )
    }
}