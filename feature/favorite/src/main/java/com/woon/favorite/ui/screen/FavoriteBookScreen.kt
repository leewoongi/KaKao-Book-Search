package com.woon.favorite.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import com.woon.core.design.card.BHorizontalCard
import com.woon.core.design.textview.BTextView
import com.woon.favorite.model.BookUiModel
import com.woon.favorite.ui.screen.footer.PagingLoadStateFooter

@Composable
fun FavoriteBookScreen(
    modifier: Modifier = Modifier,
    item: LazyPagingItems<BookUiModel>,
    onClickFavorite: (BookUiModel) -> Unit = {},
    onClick: () -> Unit = {}
){
    val listState = rememberLazyListState()
    Column(
        modifier = modifier
    ) {
        BTextView(
            text = "즐겨찾기 목록",
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
            state = listState,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ){
            items(
                count = item.itemCount,
            ){ index ->
                item[index]?.let { book ->
                    BHorizontalCard(
                        title = book.title,
                        thumbnail = book.image,
                        authors = book.authors,
                        time = book.time,
                        publisher = book.publisher,
                        isSelect = book.isFavorite,
                        price = book.price,
                        salePrice = book.salePrice,
                        isDisCount = book.isDiscount,
                        onClick = { onClick() },
                        onIconClick = { onClickFavorite(book) }
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