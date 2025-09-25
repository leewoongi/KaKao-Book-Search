package com.woon.favorite

import androidx.compose.runtime.Composable
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
import com.woon.favorite.ui.ErrorScreen
import com.woon.favorite.ui.LoadingScreen
import com.woon.favorite.ui.SuccessScreen
import com.woon.favorite.ui.bottomsheet.PriceFilterBottomSheet

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier
){
    val navController = LocalNavController.current

    val viewModel = hiltViewModel<FavoriteViewModel>()
    val books = viewModel.books.collectAsLazyPagingItems()

    var showPriceFilter by remember { mutableStateOf(false) }
    var priceRange by remember { mutableStateOf(0 to Int.MAX_VALUE) }

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
                onClick = { navController.navigate("detail") }
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