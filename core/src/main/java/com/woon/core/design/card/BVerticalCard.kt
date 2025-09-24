package com.woon.core.design.card

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.woon.core.design.badge.BBadge
import com.woon.core.design.textview.BTextView

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun BVerticalCard(
    modifier: Modifier = Modifier,
    title: String,
    thumbnail: String,
    price: String,
    salePrice: String,
    isDisCount: Boolean,
    discount: String,
    onClick: () -> Unit,
) {
    Card(
        modifier = modifier
            .width(140.dp)
            .height(210.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(6.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp,
            hoveredElevation = 8.dp
        ),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ) {
                GlideImage(
                    model = thumbnail,
                    contentDescription = "Book Image",
                    modifier = Modifier.fillMaxSize()
                )

                BBadge(
                    modifier = Modifier
                        .shadow(elevation = 4.dp)
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    Color(0xFFFF4458),
                                    Color(0xFFE50914)
                                )
                            )
                        )
                        .align(Alignment.TopStart),
                    content = discount,
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFE5E7EB))
                    .padding(10.dp)
            ) {
                BTextView(
                    text = title,
                    color = Color(0xFF111827),
                    textStyle = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )

                Spacer(modifier = Modifier.height(1.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if(isDisCount) {
                        BTextView(
                            text = salePrice,
                            color = Color(0xFFFF4458),
                            textStyle = TextStyle(
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            ),
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                    }

                    BTextView(
                        text = price,
                        color = Color(0xFF111827),
                        textStyle = TextStyle(
                            fontSize = 8.sp,
                        ),
                        textDecoration = if(isDisCount) TextDecoration.LineThrough else TextDecoration.None
                    )
                }
            }
        }
    }
}