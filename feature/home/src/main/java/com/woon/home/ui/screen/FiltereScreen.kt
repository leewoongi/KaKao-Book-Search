package com.woon.home.ui.screen

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
import com.woon.home.model.SearchFilterStatus

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
            text = "정확도순",
            color = Color(0xFF111827),
            textStyle = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.clickable(
                onClick = { onClick(SearchFilterStatus.ACCURACY) }
            )
        )

        Spacer(modifier = Modifier.width(8.dp))

        BTextView(
            text = "발간일순",
            color = Color(0xFF111827),
            textStyle = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.clickable(
                onClick = { onClick(SearchFilterStatus.LATEST) }
            )
        )
    }
}