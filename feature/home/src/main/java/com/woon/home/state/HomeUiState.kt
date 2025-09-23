package com.woon.home.state

import com.woon.domain.book.entity.Book

sealed class HomeUiState {
    object Loading : HomeUiState()
    data class Success(
        val books: List<Book>
    ) : HomeUiState()
    data class Error(val message: String) : HomeUiState()

}