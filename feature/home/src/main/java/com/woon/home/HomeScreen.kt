package com.woon.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.woon.core.navigation.LocalNavController
import com.woon.core.navigation.LocalSnackBarHostState
import com.woon.domain.book.exception.BookException
import com.woon.home.ui.ErrorScreen
import com.woon.home.ui.LoadingScreen
import com.woon.home.ui.SuccessScreen

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {
    val navController = LocalNavController.current
    val snackBarHostState = LocalSnackBarHostState.current

    val viewModel = hiltViewModel<HomeViewModel>()
    val books = viewModel.books.collectAsLazyPagingItems()
    val query by viewModel.query.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.snackBar.collect { error ->
            val message = when (error) {
                is BookException.UpdateFailure -> "업데이트에 실패했습니다."
                else -> "알 수 없는 오류가 발생했습니다."
            }
            snackBarHostState.showSnackbar(message)
        }
    }

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
                query = query,
                onSearchTextChange = { viewModel.updateQuery(it) },
                onFilterClick = { viewModel.updateFilter(it) },
                onClickFavorite = { viewModel.updateFavorite(it) },
                onClick = { navController.navigate("detail/${it.isbn}") }
            )
        }
    }
}
