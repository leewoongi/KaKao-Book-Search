package com.woon.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.woon.core.design.textview.BBadge
import com.woon.core.design.textview.BCard
import com.woon.core.design.textview.BSearchBar
import com.woon.core.design.textview.BTextView
import com.woon.domain.book.entity.Book
import com.woon.domain.money.entity.Money

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
){
    val viewModel = hiltViewModel<HomeViewModel>()

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
                BCard(
                    onClick = {  },
                    onIconClick = {  }
                )
            }
        }

        Spacer(
            modifier = Modifier.height(16.dp)
        )
    }
}
