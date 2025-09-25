package com.woon.detail.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.woon.core.design.skeleton.Skeleton

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header Skeleton
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Back button skeleton
            Skeleton(
                modifier = Modifier.size(40.dp),
                radius = 20.dp
            )

            // Favorite button skeleton
            Skeleton(
                modifier = Modifier.size(32.dp),
                radius = 16.dp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Body Skeleton
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Book cover skeleton
            Skeleton(
                modifier = Modifier
                    .width(180.dp)
                    .height(260.dp),
                radius = 8.dp
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Title skeleton
            Skeleton(
                modifier = Modifier
                    .width(200.dp)
                    .height(20.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Author skeleton
            Skeleton(
                modifier = Modifier
                    .width(150.dp)
                    .height(16.dp)
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Publisher & Date skeleton
            Skeleton(
                modifier = Modifier
                    .width(120.dp)
                    .height(14.dp)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Price row skeleton
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                // Main price
                Skeleton(
                    modifier = Modifier
                        .width(80.dp)
                        .height(20.dp)
                )

                Spacer(modifier = Modifier.width(8.dp))

                // Original price
                Skeleton(
                    modifier = Modifier
                        .width(60.dp)
                        .height(16.dp)
                )

                Spacer(modifier = Modifier.width(8.dp))

                // Discount badge
                Skeleton(
                    modifier = Modifier
                        .width(40.dp)
                        .height(20.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        HorizontalDivider(
            modifier = Modifier
                .height(2.dp)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Footer Skeleton
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            // 책소개 title skeleton
            Skeleton(
                modifier = Modifier
                    .width(60.dp)
                    .height(20.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Description lines skeleton (3 lines)
            Skeleton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(14.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))

            Skeleton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(14.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))

            Skeleton(
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .height(14.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // 상세 정보 title skeleton
            Skeleton(
                modifier = Modifier
                    .width(80.dp)
                    .height(20.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Detail items skeleton (4 rows)
            repeat(4) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Skeleton(
                        modifier = Modifier
                            .width(50.dp)
                            .height(18.dp)
                    )

                    Skeleton(
                        modifier = Modifier
                            .width(100.dp)
                            .height(18.dp)
                    )
                }
            }
        }
    }
}