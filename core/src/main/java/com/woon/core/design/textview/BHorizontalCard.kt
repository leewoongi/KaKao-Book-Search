package com.woon.core.design.textview

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import java.util.Date

@Composable
fun BHorizontalCard(
    // Book 데이터 필드들
    title: String,
    authors: List<String>,
    contents: String,
    time: Date,
    isbn: String,
    salePrice: Money,
    price: Money,
    salePercent: Double,
    publisher: String,
    status: BookStatus,
    image: String,
    translators: List<String>,
    url: String,
    isFavorite: Boolean,
    // 이벤트 콜백
    onCardClick: () -> Unit,
    onFavoriteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .width(130.dp)
            .clip(RoundedCornerShape(8.dp))
            .clickable { onCardClick() }
    ) {
        // 책 표지 이미지 + 배지
        Box(
            modifier = Modifier
                .width(130.dp)
                .height(190.dp)
        ) {
            // Glide 이미지 로딩
            GlideImage(
                model = image,
                contentDescription = title,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFF2A2A2A)),
                contentScale = ContentScale.Crop,
                loading = placeholder(R.drawable.book_placeholder),
                failure = placeholder(R.drawable.book_error)
            )

            // 할인 배지 (좌상단)
            if (salePercent > 0) {
                Box(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(8.dp)
                        .shadow(
                            elevation = 4.dp,
                            shape = RoundedCornerShape(4.dp)
                        )
                        .background(
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    Color(0xFFFF4458),
                                    Color(0xFFE50914)
                                )
                            ),
                            shape = RoundedCornerShape(4.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    BTextView(
                        text = "${salePercent.toInt()}%",
                        color = Color.White,
                        textStyle = TextStyle(
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }

            // 판매 상태 배지 (우상단)
            if (status != BookStatus.AVAILABLE) {
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .background(
                            color = Color(0xB3000000),
                            shape = RoundedCornerShape(4.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    BTextView(
                        text = when(status) {
                            BookStatus.OUT_OF_STOCK -> "품절"
                            BookStatus.DISCONTINUED -> "절판"
                            BookStatus.PREORDER -> "예약판매"
                            else -> "판매중"
                        },
                        color = Color.White,
                        textStyle = TextStyle(
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Medium
                        )
                    )
                }
            }

            // 즐겨찾기 버튼 (우하단)
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(8.dp)
                    .size(32.dp)
                    .background(
                        color = Color(0xE6000000),
                        shape = CircleShape
                    )
                    .clickable { onFavoriteClick() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = if (isFavorite) {
                        Icons.Filled.Star
                    } else {
                        Icons.Outlined.StarBorder
                    },
                    contentDescription = "Favorite",
                    modifier = Modifier.size(18.dp),
                    tint = if (isFavorite) {
                        Color(0xFFFFD700)
                    } else {
                        Color(0x99FFFFFF)
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // 책 정보
        Column(
            modifier = Modifier.padding(horizontal = 4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            // 제목
            BTextView(
                text = title,
                color = Color.White,
                textStyle = TextStyle(
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            // 가격 정보
            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // 원가 (할인이 있을 때만 표시)
                if (salePercent > 0) {
                    BTextView(
                        text = price.formatted(), // "₩15,900" 형태로 포맷
                        color = Color(0x66FFFFFF),
                        textStyle = TextStyle(fontSize = 12.sp),
                        textDecoration = TextDecoration.LineThrough
                    )
                }

                // 판매가
                BTextView(
                    text = salePrice.formatted(), // "₩14,310" 형태로 포맷
                    color = if (salePercent > 0) {
                        Color(0xFF46D369)  // 할인가 초록색
                    } else {
                        Color(0xCCFFFFFF)  // 정상가 흰색
                    },
                    textStyle = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }
        }
    }
}