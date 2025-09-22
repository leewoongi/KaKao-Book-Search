package com.woon.core.design.textview

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun BTextView(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.Unspecified,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip,
    textAlign:TextAlign = TextAlign.Start,
    textDecoration: TextDecoration? = null,
){
    Text(
        text = text,
        modifier = modifier,
        color = color,
        softWrap = true,
        textAlign = textAlign,
        maxLines = maxLines,
        overflow = overflow,
        textDecoration = textDecoration
    )
}