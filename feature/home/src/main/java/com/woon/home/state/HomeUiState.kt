package com.woon.home.state

import com.woon.home.model.BookUiModel

sealed class HomeUiState {
    object Loading : HomeUiState()
    object Empty : HomeUiState()
    data class Success(
        val books: List<BookUiModel>,
        val topDiscountedBooks: List<BookUiModel>
    ) : HomeUiState()
    data class Error(val exception : Throwable) : HomeUiState()

}