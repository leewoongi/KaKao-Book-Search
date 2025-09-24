package com.woon.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.woon.home.ui.ErrorScreen
import com.woon.home.ui.LoadingScreen
import com.woon.home.ui.SuccessScreen

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
){
    val viewModel = hiltViewModel<HomeViewModel>()
    val books = viewModel.books.collectAsLazyPagingItems()

    when (books.loadState.refresh) {
        is LoadState.Loading -> {
            LoadingScreen()
        }

        is LoadState.Error -> {
            val error = books.loadState.refresh as LoadState.Error
            ErrorScreen(
                error = error.error,
                onClick = { books.retry() }
            )
        }
        is LoadState.NotLoading -> {
            SuccessScreen(
                books = books,
                onSearchTextChange = { viewModel.updateQuery(it) },
                onFilterClick = { viewModel.updateFilter(it) },
                onClickFavorite = { viewModel.updateFavorite(it) }
            )
        }
    }
}
