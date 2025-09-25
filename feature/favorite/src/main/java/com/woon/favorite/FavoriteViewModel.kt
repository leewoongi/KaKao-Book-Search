package com.woon.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.woon.domain.book.usecase.GetBooksUseCase
import com.woon.domain.book.usecase.ToggleFavoriteUseCase
import com.woon.favorite.mapper.toDomain
import com.woon.favorite.mapper.toUiModel
import com.woon.favorite.model.BookUiModel
import com.woon.favorite.model.SearchFilterStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel
@Inject constructor(
    private val getBooksUseCase: GetBooksUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
): ViewModel() {

    private val _query = MutableStateFlow("")
    val query = _query.asStateFlow()

    private val _filter = MutableStateFlow(SearchFilterStatus.DESCENDING)
    val filter = _filter.asStateFlow()

    private val _range = MutableStateFlow(0 to Int.MAX_VALUE)
    val range = _range.asStateFlow()

    private val _books = MutableStateFlow<PagingData<BookUiModel>>(PagingData.empty())
    val books = _books.asStateFlow()
    private val _snackBar = MutableSharedFlow<Throwable>()
    val snackBar = _snackBar.asSharedFlow()

    private val errorHandler = CoroutineExceptionHandler { _, throwable ->
        viewModelScope.launch {
            _snackBar.emit(throwable)
        }
    }

    init {
        getBooks()
    }

    private fun getBooks() {
        viewModelScope.launch {
            getBooksUseCase.getLocalBooks(
                query = query.value,
                filter = filter.value.value,
                range = range.value
            ).map { pagingData ->
                pagingData.map { it.toUiModel() }
            }.cachedIn(viewModelScope).collect { bookUiModel ->
                _books.value = bookUiModel
            }
        }
    }

    fun updateQuery(query: String) {
        _query.value = query
        getBooks()
    }

    fun updateFilter(filter: SearchFilterStatus) {
        _filter.value = filter
        getBooks()
    }

    fun updatePriceRange(min: Int, max: Int) {
        _range.value = min to max
        getBooks()
    }

    fun updateFavorite(bookUiModel: BookUiModel) {
        viewModelScope.launch(errorHandler) {
            val book = bookUiModel.copy(
                isFavorite = !bookUiModel.isFavorite
            )
            toggleFavoriteUseCase.invoke(book.toDomain())
        }
    }
}