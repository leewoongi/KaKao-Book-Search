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
import com.woon.detail.model.BookUiModel

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun BodyScreen(
    modifier: Modifier = Modifier,
    item: BookUiModel
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
                model = item.image,
                contentDescription = "Book Cover",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        BTextView(
            text = item.title,
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
            text = item.authors,
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
            text = "${item.publisher} * ${item.time}",
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
                text = item.salePrice,
                color = Color(0xFF111827),
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            )

            Spacer(modifier = Modifier.width(8.dp))

            BTextView(
                text = item.price,
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
                    text = item.salePercent,
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