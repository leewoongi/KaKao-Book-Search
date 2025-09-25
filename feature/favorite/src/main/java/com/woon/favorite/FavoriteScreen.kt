package com.woon.favorite

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.woon.core.navigation.LocalNavController
import com.woon.core.navigation.LocalSnackBarHostState
import com.woon.domain.book.exception.BookException
import com.woon.favorite.ui.ErrorScreen
import com.woon.favorite.ui.LoadingScreen
import com.woon.favorite.ui.SuccessScreen
import com.woon.favorite.ui.bottomsheet.PriceFilterBottomSheet

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier
){
    val navController = LocalNavController.current
    val snackBarHostState = LocalSnackBarHostState.current

    val viewModel = hiltViewModel<FavoriteViewModel>()
    val books = viewModel.books.collectAsLazyPagingItems()

    var showPriceFilter by remember { mutableStateOf(false) }
    var priceRange by remember { mutableStateOf(0 to Int.MAX_VALUE) }

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
                onSearchTextChange = { viewModel.updateQuery(it) },
                onClickSortFilter = { viewModel.updateFilter(it) },
                onClickFavorite = { viewModel.updateFavorite(it) },
                onClickPriceFilter = { showPriceFilter = true },
                onClick = { navController.navigate("detail/${it.isbn}") }
            )
        }
    }

    if (showPriceFilter) {
        PriceFilterBottomSheet(
            onDismiss = { showPriceFilter = false },
            onApply = { min, max ->
                priceRange = min to max
                viewModel.updatePriceRange(min, max)
            },
            currentMin = priceRange.first,
            currentMax = priceRange.second
        )
    }
}