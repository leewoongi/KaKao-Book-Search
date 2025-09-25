package com.woon.favorite.ui.bottomsheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.woon.core.design.button.BIconButton
import com.woon.core.design.textview.BTextView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PriceFilterBottomSheet(
    onDismiss: () -> Unit,
    onApply: (min: Int, max: Int) -> Unit,
    currentMin: Int = 0,
    currentMax: Int = 100000
) {
    val sheetState = rememberModalBottomSheetState()
    var minPrice by remember { mutableStateOf(currentMin.toFloat()) }
    var maxPrice by remember { mutableStateOf(currentMax.toFloat()) }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BTextView(
                    text = "가격 필터",
                    textStyle = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    ),
                )
                BIconButton(
                    icon = Icons.Default.Close,
                    onClick = { onDismiss() }
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        BTextView(
                            text = "최소",
                            textStyle = TextStyle(
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium
                            ),
                        )

                        BTextView(
                            text = "${minPrice.toInt()}원",
                            textStyle = TextStyle(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }

                    Icon(
                        Icons.Default.ArrowForward,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.5f)
                    )

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        BTextView(
                            text = "최대",
                            textStyle = TextStyle(
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium
                            ),
                        )

                        BTextView(
                            text = "${maxPrice.toInt()}원",
                            textStyle = TextStyle(
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 빠른 선택 옵션
            BTextView(
                text = "빠른 선택",
                textStyle = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val quickRanges = listOf(
                    "전체" to (0 to Int.MAX_VALUE),
                    "~1만원" to (0 to 10000),
                    "1~3만원" to (10000 to 30000),
                    "3~5만원" to (30000 to 50000),
                    "5만원~" to (50000 to Int.MAX_VALUE)
                )

                items(quickRanges.size) { index ->
                    val (label, range) = quickRanges[index]
                    FilterChip(
                        selected = minPrice.toInt() == range.first && maxPrice.toInt() == range.second,
                        onClick = {
                            minPrice = range.first.toFloat()
                            maxPrice = range.second.toFloat()
                        },
                        label = {
                            BTextView(
                                text = label,
                                textStyle = TextStyle(
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            )
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // 적용 버튼
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedButton(
                    onClick = {
                        minPrice = 0f
                        maxPrice = 100000f
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    BTextView(
                        text = "초기화",
                        textStyle = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )
                    )
                }

                Button(
                    onClick = {
                        onApply(minPrice.toInt(), maxPrice.toInt())
                        onDismiss()
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    BTextView(
                        text = "적용",
                        textStyle = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )
                    )
                }
            }
        }
    }
}