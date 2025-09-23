package com.woon.home.state

import com.woon.home.model.BookUiModel

sealed class HomeUiState {
    object Loading : HomeUiState()
    data class Success(
        val books: List<BookUiModel>,
        val topDiscountedBooks: List<BookUiModel>
    ) : HomeUiState()
    data class Error(val message: String) : HomeUiState()

}