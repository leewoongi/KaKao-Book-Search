package com.woon.home.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import com.woon.core.design.card.BHorizontalCard
import com.woon.core.design.textview.BTextView
import com.woon.home.model.BookUiModel
import com.woon.home.ui.screen.footer.PagingLoadStateFooter

@Composable
fun SearchBookScreen(
    modifier: Modifier = Modifier,
    item: LazyPagingItems<BookUiModel>
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
                count = item.itemCount,
                key = item.itemKey { it.isbn }
            ){ index ->
                item[index]?.let { book ->
                    BHorizontalCard(
                        title = book.title,
                        thumbnail = book.image,
                        authors = book.authors,
                        time = book.time,
                        publisher = book.publisher,
                        isSelect = false,
                        price = book.price,
                        salePrice = book.salePrice,
                        isDisCount = book.isDiscount,
                        onClick = {  },
                        onIconClick = {  }
                    )
                }
            }

            item  {
                PagingLoadStateFooter(
                    loadState = item.loadState.append,
                    onRetryClick = { item.retry() }
                )
            }
        }
    }
}