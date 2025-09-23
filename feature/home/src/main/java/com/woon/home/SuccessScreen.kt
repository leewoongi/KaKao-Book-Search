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
import com.woon.core.design.card.BHorizontalCard
import com.woon.core.design.card.BVerticalCard
import com.woon.core.design.textview.BSearchBar
import com.woon.core.design.textview.BTextView
import com.woon.home.model.BookUiModel
import com.woon.home.ui.DisCountBookScreen

@Composable
fun SuccessScreen(
    modifier: Modifier = Modifier,
    books: List<BookUiModel>,
    topDiscountedBooks: List<BookUiModel>
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

        DisCountBookScreen(
            modifier = modifier,
            item = topDiscountedBooks
        )

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
                BHorizontalCard(
                    onClick = {  },
                    onIconClick = {  }
                )
            }
        }
    }
}