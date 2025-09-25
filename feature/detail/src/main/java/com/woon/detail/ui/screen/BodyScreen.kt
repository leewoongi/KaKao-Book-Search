package com.woon.detail.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.woon.core.design.textview.BTextView

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun BodyScreen(
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Card(
            modifier = Modifier
                .width(180.dp)
                .height(260.dp)
                .align(Alignment.CenterHorizontally)
                .shadow(12.dp, RoundedCornerShape(8.dp)),
            shape = RoundedCornerShape(8.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            GlideImage(
                model = "data:image/jpeg;base64,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",
                contentDescription = "Book Cover",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        BTextView(
            text = "코틀린 프로그래밍 마스터",
            textStyle = TextStyle(
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold
            ),
            color = Color(0xFF111827),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Spacer(modifier = Modifier.height(4.dp))

        BTextView(
            text = "김코틀린, 박안드로이드",
            textStyle = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            ),
            color = Color(0xFF6B7280),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        Spacer(modifier = Modifier.height(4.dp))

        BTextView(
            text = "한빛미디어 * 2023.01.01",
            textStyle = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal
            ),
            color = Color(0xFF9CA3AF),
        )

        Spacer(modifier = Modifier.height(4.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            BTextView(
                text = "27,000원",
                color = Color(0xFF111827),
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            )

            Spacer(modifier = Modifier.width(8.dp))

            BTextView(
                text = "32,000원",
                textStyle = TextStyle(
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal
                ),
                color = Color(0xFF9CA3AF),
                textDecoration = TextDecoration.LineThrough
            )

            Spacer(modifier = Modifier.width(8.dp))

            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFFF4458)
                ),
                shape = RoundedCornerShape(4.dp)
            ) {
                BTextView(
                    text = "10%",
                    color = Color.White,
                    textStyle = TextStyle(
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                )
            }
        }
    }
}