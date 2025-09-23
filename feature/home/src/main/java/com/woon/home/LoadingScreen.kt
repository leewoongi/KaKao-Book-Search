package com.woon.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.woon.core.design.card.BHorizontalSkeletonCard
import com.woon.core.design.card.BVerticalSkeletonCard
import com.woon.core.design.textview.BSearchBar
import com.woon.core.design.textview.BTextView

@Composable
fun LoadingScreen(
    modifier: Modifier = Modifier
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        BSearchBar(
            modifier = modifier
                .fillMaxWidth(),
            onSearchTextChange = {}
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        BTextView(
            text = "오늘의 할인",
            color = Color.Black,
            textStyle = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(5) { book ->
                BVerticalSkeletonCard()
            }
        }

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        BTextView(
            text = "검색 목록",
            color = Color.Black,
            textStyle = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){
            items(
                count = 10,
            ) { book ->
                BHorizontalSkeletonCard()
            }
        }
    }
}