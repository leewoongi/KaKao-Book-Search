package com.woon.domain.book.usecase

import com.woon.domain.book.entity.Book
import com.woon.domain.book.exception.BookException
import com.woon.domain.book.repository.BookRepository
import java.net.UnknownHostException
import javax.inject.Inject

class GetBooksUseCase
@Inject constructor(
    private val bookRepository: BookRepository
){
    suspend operator fun invoke(
        query: String,
        filter: String
    ) : List<Book> {
        return try {
            bookRepository.getBooks(query, filter)
        } catch (e: UnknownHostException) {
            throw BookException.Network(e)
        } catch (e: Exception) {
            throw BookException.Unknown(e)
        }
    }
}