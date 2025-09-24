package com.woon.home.ui.screen.footer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import com.woon.core.design.card.BHorizontalSkeletonCard
import com.woon.core.design.skeleton.Skeleton
import java.net.UnknownHostException

@Composable
fun PagingLoadStateFooter(
    modifier: Modifier = Modifier,
    loadState: LoadState,
    onRetryClick: () -> Unit
) {
    when (loadState) {
        is LoadState.Loading -> {
            Box(
                modifier = modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                BHorizontalSkeletonCard()
            }
        }

        is LoadState.Error -> {
            val e = loadState.error
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = when (e) {
                            is UnknownHostException -> "네트워크 연결을 확인해 주세요"
                            else -> "알 수 없는 오류가 발생했습니다"
                        },
                        color = Color.Red,
                        textAlign = TextAlign.Center
                    )
                    TextButton(onClick = onRetryClick) {
                        Text("재시도")
                    }
                }
            }
        }

        is LoadState.NotLoading -> {}
    }
}