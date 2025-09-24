package com.woon.domain.book.usecase

import androidx.paging.PagingData
import com.woon.domain.book.entity.Book
import com.woon.domain.book.exception.BookException
import com.woon.domain.book.repository.BookRepository
import kotlinx.coroutines.flow.Flow
import java.net.UnknownHostException
import javax.inject.Inject

class GetBooksUseCase
@Inject constructor(
    private val bookRepository: BookRepository
){
    operator fun invoke(
        query: String,
        filter: String
    ) : Flow<PagingData<Book>> {
        return bookRepository.getBookList(query, filter)
    }
}