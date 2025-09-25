package com.woon.domain.book.usecase

import androidx.paging.PagingData
import com.woon.domain.book.entity.Book
import com.woon.domain.book.entity.SortType
import com.woon.domain.book.repository.BookRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetBooksUseCase
@Inject constructor(
    private val bookRepository: BookRepository
) {
    fun getRemoteBooks(
        query: String,
        filter: String
    ): Flow<PagingData<Book>> {
        return bookRepository.getRemote(query, filter)
    }

    fun getLocalBooks(
        query: String,
        filter: String,
        range: Pair<Int, Int>
    ): Flow<PagingData<Book>> {
        return bookRepository.getLocal(
            query = query,
            filter = SortType.from(filter),
            range = range
        )
    }
}