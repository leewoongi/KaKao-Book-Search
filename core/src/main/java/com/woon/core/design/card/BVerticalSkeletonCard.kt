package com.woon.core.design.card

import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.woon.core.design.skeleton.Skeleton

@Composable
fun BVerticalSkeletonCard(){
    Column(
        modifier = Modifier
            .width(140.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White)
    ) {
        // 이미지 영역
        Box {
            Skeleton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                radius = 0.dp
            )

            // 즐겨찾기 버튼 위치
            Skeleton(
                modifier = Modifier.size(24.dp),
                radius = 12.dp
            )
        }

        // 텍스트 영역
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            // 제목
            Skeleton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp),
                radius = 4.dp
            )

            Spacer(modifier = Modifier.height(6.dp))

            // 서브 텍스트
            Skeleton(
                modifier = Modifier
                    .width(80.dp)
                    .height(14.dp),
                radius = 4.dp
            )

            Spacer(modifier = Modifier.height(8.dp))

            // 가격 영역
            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                // 할인 가격
                Skeleton(
                    modifier = Modifier
                        .width(60.dp)
                        .height(18.dp),
                    radius = 4.dp
                )

                // 원가
                Skeleton(
                    modifier = Modifier
                        .width(40.dp)
                        .height(14.dp),
                    radius = 4.dp
                )
            }
        }
    }
}