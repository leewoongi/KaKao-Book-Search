package com.woon.detail.state

import com.woon.detail.model.BookUiModel

sealed class DetailUiState {
    object Loading: DetailUiState()
    data class Success(
        val book: BookUiModel,
        val onClickFavorite: (BookUiModel) -> Unit = {}
    ): DetailUiState()
    data class Error(
        val error: Throwable,
        val onRetry: () -> Unit
    ): DetailUiState()

}