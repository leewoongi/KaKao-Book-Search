package com.woon.domain.book.usecase

import com.woon.domain.book.entity.Book
import com.woon.domain.book.exception.BookException
import com.woon.domain.book.repository.BookRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBookByIdUseCase
@Inject constructor(
    private val bookRepository: BookRepository
) {
    operator fun invoke(id: String): Flow<Book> {
        return try {
            bookRepository.getBookById(id)
        }catch (e: Exception) {
            throw BookException.Unknown(e)
        }
    }
}