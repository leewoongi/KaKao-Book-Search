package com.woon.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.woon.domain.book.usecase.GetBooksUseCase
import com.woon.home.state.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(
    private val getBooksUseCase: GetBooksUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        getBooks()
    }

    private fun getBooks() {
        viewModelScope.launch {
            _uiState.value = HomeUiState.Loading
            val result = getBooksUseCase.invoke()
            delay(1000)
            _uiState.value = HomeUiState.Success(result)
        }
    }
}