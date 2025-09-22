package com.woon.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.woon.domain.book.usecase.GetBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(
    private val getBooksUseCase: GetBooksUseCase
): ViewModel() {

    init {
        getBooks()
    }

    private fun getBooks() {
        viewModelScope.launch {
            getBooksUseCase.invoke()
        }
    }
}