package com.woon.core.design.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.woon.core.design.skeleton.Skeleton

@Composable
fun BHorizontalSkeletonCard(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White)
            .padding(12.dp)
    ) {
        // 이미지 영역
        Skeleton(
            modifier = Modifier
                .width(90.dp)
                .fillMaxHeight(),
            radius = 6.dp
        )

        Spacer(modifier = Modifier.width(12.dp))

        // 콘텐츠 영역
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
        ) {
            Column {
                Skeleton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(34.dp),
                    radius = 4.dp
                )

                Spacer(modifier = Modifier.height(4.dp))

                Skeleton(
                    modifier = Modifier
                        .width(120.dp)
                        .height(14.dp),
                    radius = 4.dp
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Column {
                    Skeleton(
                        modifier = Modifier
                            .width(70.dp)
                            .height(18.dp),
                        radius = 4.dp
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Skeleton(
                        modifier = Modifier
                            .width(50.dp)
                            .height(14.dp),
                        radius = 4.dp
                    )
                }
            }
        }
    }
}