package com.woon.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.woon.core.design.card.BHorizontalCard
import com.woon.core.design.textview.BTextView
import com.woon.home.model.BookUiModel

@Composable
fun SearchBookScreen(
    modifier : Modifier = Modifier,
    item : List<BookUiModel>
){
    Column(
        modifier = modifier
    ) {
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
                items = item,
                key = { book -> book.isbn }
            ){
                BHorizontalCard(
                    title = it.title,
                    thumbnail = it.image,
                    authors = it.authors,
                    time = it.time,
                    publisher = it.publisher,
                    isSelect = false,
                    price = it.price,
                    salePrice = it.salePrice,
                    isDisCount = it.isDiscount,
                    onClick = {  },
                    onIconClick = {  }
                )
            }
        }
    }
}