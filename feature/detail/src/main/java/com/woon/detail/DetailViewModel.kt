package com.woon.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.woon.detail.mapper.toDomain
import com.woon.detail.mapper.toUiModel
import com.woon.detail.model.BookUiModel
import com.woon.detail.state.DetailUiState
import com.woon.domain.book.usecase.GetBookByIdUseCase
import com.woon.domain.book.usecase.RemoveFavoriteBookUseCase
import com.woon.domain.book.usecase.ToggleFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel
@Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getBookUseCase: GetBookByIdUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
    private val removeFavoriteUseCase: RemoveFavoriteBookUseCase
) : ViewModel() {

    private val bookId: String = checkNotNull(savedStateHandle["bookId"])
    private val _book = MutableStateFlow<DetailUiState>(DetailUiState.Loading)
    val book = _book.asStateFlow()

    private val errorHandle = CoroutineExceptionHandler { _, throwable ->
        _book.value = DetailUiState.Error(
            error = throwable,
            onRetry = { getBook() }
        )
    }

    init {
        getBook()
    }

    private fun getBook() {
        viewModelScope.launch(errorHandle) {
            _book.value = DetailUiState.Loading
            delay(1000)
            getBookUseCase.invoke(bookId).collect { book ->
                val result = book.toUiModel()
                _book.value = DetailUiState.Success(
                    book = result,
                    onClickFavorite = { updateFavorite(result) }
                )
            }
        }
    }

    private fun updateFavorite(bookUiModel: BookUiModel) {
        viewModelScope.launch {
            val book = bookUiModel.copy(
                isFavorite = !bookUiModel.isFavorite
            )
            toggleFavoriteUseCase.invoke(book.toDomain())
        }
    }

    fun remove() {
        viewModelScope.launch {
            removeFavoriteUseCase.invoke()
        }
    }
}