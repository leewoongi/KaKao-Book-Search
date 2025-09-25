package com.woon.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.woon.domain.book.usecase.GetBooksUseCase
import com.woon.domain.book.usecase.ToggleFavoriteUseCase
import com.woon.home.mapper.toDomain
import com.woon.home.mapper.toUiModel
import com.woon.home.model.BookUiModel
import com.woon.home.model.SearchFilterStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel
@Inject constructor(
    private val getBooksUseCase: GetBooksUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {

    private val _query = MutableStateFlow("")
    val query = _query.asStateFlow()

    private val _filter = MutableStateFlow(SearchFilterStatus.ACCURACY)
    val filter = _filter.asStateFlow()

    private val _snackBar = MutableSharedFlow<Throwable>()
    val snackBar = _snackBar.asSharedFlow()

    private val errorHandler = CoroutineExceptionHandler { _, throwable ->
        viewModelScope.launch {
            _snackBar.emit(throwable)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val books: Flow<PagingData<BookUiModel>> =
        combine(
            query,
            filter
        ) { query, filter ->
            Pair(query, filter)
        }.flatMapLatest { (query, filter) ->
            getBooksUseCase.getRemoteBooks(query, filter.value)
                .map { pagingData ->
                    pagingData.map { it.toUiModel() }
                }
        }.cachedIn(viewModelScope)

    fun updateQuery(query: String) {
        _query.value = query
    }

    fun updateFilter(filter: SearchFilterStatus) {
        _filter.value = filter
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