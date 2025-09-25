package com.woon.favorite

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.woon.favorite.ui.ErrorScreen
import com.woon.favorite.ui.LoadingScreen
import com.woon.favorite.ui.SuccessScreen

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier
){
    val viewModel = hiltViewModel<FavoriteViewModel>()
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