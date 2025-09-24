package com.woon.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.woon.domain.book.exception.BookException
import com.woon.domain.book.usecase.GetBooksUseCase
import com.woon.domain.book.usecase.GetTopDiscountedBooksUseCase
import com.woon.home.mapper.toUiModel
import com.woon.home.model.BookUiModel
import com.woon.home.model.SearchFilterStatus
import com.woon.home.state.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.paging.map
import com.woon.domain.book.entity.Book
import kotlinx.coroutines.flow.map


@HiltViewModel
class HomeViewModel
@Inject constructor(
    private val getBooksUseCase: GetBooksUseCase,
    private val getTopDiscountedBooksUseCase: GetTopDiscountedBooksUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState = _uiState.asStateFlow()
    private val exceptionHandler = CoroutineExceptionHandler { context, throwable ->
        // 에러 처리 로직
        _uiState.value = HomeUiState.Error(
            exception = throwable,
            onClick = { retry() }
        )
    }

    private val _query = MutableStateFlow("")
    val query = _query.asStateFlow()

    private val _filter = MutableStateFlow(SearchFilterStatus.ACCURACY)
    val filter = _filter.asStateFlow()

    val books: StateFlow<PagingData<BookUiModel>> =
        getBooksUseCase.getBookList(query.value, filter.value.value)
            .map { pagingData: PagingData<Book> ->
                pagingData.map { book -> book.toUiModel() }
            }
            .cachedIn(viewModelScope)
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000),
                PagingData.empty()
            )

    init {
//        getBooks()
    }

    private fun getBooks() {
        viewModelScope.launch(exceptionHandler) {
            _uiState.value = HomeUiState.Loading

//            val result = getBooksUseCase.invoke(
//                query = searchQuery.value,
//                filter = filter.value.value
//            )

//            val result = getBooksUseCase.invoke(
//                query = searchQuery.value,
//                filter = filter.value.value
//            )
//            val books = result.map { it.toUiModel() }
//            val topDiscountedBooks = getTopDiscountedBooksUseCase.invoke(
//                books = result,
//                limit = 5
//            ).map { it.toUiModel() }
//            delay(1000)
//
//            _uiState.value = HomeUiState.Success(
//                books = books,
//                topDiscountedBooks = topDiscountedBooks,
//                onSearchTextChange = { query ->
//                    _searchQuery.value = query
//                    getBooks()
//                },
//                onFilterClick = { filterStatus ->
//                    _filter.value = filterStatus
//                    getBooks()
//                }
//            )
        }
    }

    private fun retry() {
        getBooks()
    }
}