package com.woon.domain.book.usecase

import androidx.paging.PagingData
import androidx.paging.map
import com.woon.domain.book.entity.Book
import com.woon.domain.book.repository.BookRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetBooksUseCase
@Inject constructor(
    private val bookRepository: BookRepository
) {
    fun getRemoteBooks(
        query: String,
        filter: String
    ): Flow<PagingData<Book>> {
        return bookRepository.getRemoteBooks(query, filter)
    }

    fun getLocalBooks(
        query: String,
        filter: String
    ): Flow<PagingData<Book>> {
        return bookRepository.getLocalBooks(query, filter)
    }
}