package com.woon.core.design.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.woon.core.design.button.BIconButton
import com.woon.core.design.badge.BBadge
import com.woon.core.design.textview.BTextView

@Composable
fun BVerticalCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onIconClick: () -> Unit,
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
                // 이미지 넣어야 함
                Image(
                    modifier = Modifier.fillMaxSize(),
                    imageVector = Icons.Default.Close,
                    contentScale = ContentScale.Crop,
                    contentDescription = "Book Image"
                )

                // 할인 배지
                BBadge(
                    modifier = Modifier
                        .shadow(
                            elevation = 4.dp,
                            shape = RoundedCornerShape(4.dp)
                        )
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    Color(0xFFFF4458),  // 밝은 빨강
                                    Color(0xFFE50914)   // 어두운 빨강
                                )
                            ),
                            shape = RoundedCornerShape(4.dp)
                        )
                        .align(Alignment.Center),
                    percent = 50,
                )

                // 즐겨찾기 버튼
                BIconButton(
                    modifier = Modifier
                        .padding(4.dp)
                        .background(
                            color = Color.Black.copy(alpha = 0.6f),
                            shape = CircleShape
                        )
                        .border(
                            width = 1.dp,
                            color = Color.White.copy(alpha = 0.3f),
                            shape = CircleShape
                        )
                        .size(24.dp)
                        .align(Alignment.TopEnd),
                    icon = Icons.Filled.Favorite,
//                icon = if (book.isFavorite) Icons.Filled.Favorite else Icons.Default.FavoriteBorder,
                    iconColor = Color.White,
//                iconColor = if (book.isFavorite) Color(0xFFE50914) else Color.White,
                    onClick = { onIconClick() }
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFE5E7EB))
                    .padding(10.dp)
            ) {
                BTextView(
                    text = "TEST TEST TEST",
                    color = Color(0xFF111827),
                    textStyle = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // 할인 가격
                    BTextView(
                        text = "₩9,900",
                        color = Color(0xFF111827),
                        textStyle = TextStyle(
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold
                        ),
                    )

                    Spacer(modifier = Modifier.width(4.dp))

                    // 정가
                    BTextView(
                        text = "₩19,800",
                        color = Color(0xFF111827),
                        textStyle = TextStyle(
                            fontSize = 11.sp,
                        ),
                        textDecoration = TextDecoration.LineThrough
                    )
                }
            }
        }
    }
}