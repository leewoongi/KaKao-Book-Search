package com.woon.home.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.woon.core.design.textview.BSearchBar
import com.woon.home.model.BookUiModel
import com.woon.home.model.SearchFilterStatus
import com.woon.home.ui.screen.FilterScreen
import com.woon.home.ui.screen.SearchBookScreen

@Composable
fun SuccessScreen(
    modifier: Modifier = Modifier,
    books: LazyPagingItems<BookUiModel>,
    onSearchTextChange: (String) -> Unit = {},
    onFilterClick: (SearchFilterStatus) -> Unit = {},
    onClickFavorite: (BookUiModel) -> Unit = {}
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        BSearchBar(
            modifier = modifier
                .fillMaxWidth(),
            onSearchTextChange = onSearchTextChange,
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        if(books.itemCount == 0){
            EmptyScreen(
                modifier = Modifier.fillMaxSize()
            )
        } else {
            FilterScreen(
                modifier = modifier,
                onClick = { onFilterClick(it) }
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            // 검색한 도서 목록
            SearchBookScreen(
                modifier = modifier,
                item = books,
                onClickFavorite = { onClickFavorite(it)  }
            )
        }
    }
}