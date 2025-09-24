package com.woon.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.woon.domain.book.exception.BookException
import com.woon.domain.book.usecase.GetBooksUseCase
import com.woon.domain.book.usecase.GetTopDiscountedBooksUseCase
import com.woon.home.mapper.toUiModel
import com.woon.home.state.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(
    private val getBooksUseCase: GetBooksUseCase,
    private val getTopDiscountedBooksUseCase: GetTopDiscountedBooksUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState = _uiState.asStateFlow()
    private val exceptionHandler = CoroutineExceptionHandler { context, throwable ->
        // 에러 처리 로직
        _uiState.value = HomeUiState.Error(
            exception = throwable,
            onClick = { retry() }
        )
    }

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()


    init {
        getBooks()
    }

    private fun getBooks() {
        viewModelScope.launch(exceptionHandler) {
            _uiState.value = HomeUiState.Loading

            val result = getBooksUseCase.invoke(searchQuery.value)
            val books = result.map { it.toUiModel() }
            val topDiscountedBooks = getTopDiscountedBooksUseCase.invoke(
                books = result,
                limit = 5
            ).map { it.toUiModel() }
            delay(1000)

            _uiState.value = HomeUiState.Success(
                books = books,
                topDiscountedBooks = topDiscountedBooks,
                onSearchTextChange = { query ->
                    _searchQuery.value = query
                    getBooks()
                }
            )
        }
    }

    private fun retry() {
        getBooks()
    }
}