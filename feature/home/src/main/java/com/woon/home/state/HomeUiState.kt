package com.woon.home.state

import com.woon.home.model.BookUiModel
import com.woon.home.model.SearchFilterStatus

sealed class HomeUiState {
    object Loading : HomeUiState()
    data class Success(
        val books: List<BookUiModel>,
        val topDiscountedBooks: List<BookUiModel>,
        val onSearchTextChange: (String) -> Unit,
        val onFilterClick: (SearchFilterStatus) -> Unit
    ) : HomeUiState()
    data class Error(
        val exception : Throwable,
        val onClick : () -> Unit
    ) : HomeUiState()

}