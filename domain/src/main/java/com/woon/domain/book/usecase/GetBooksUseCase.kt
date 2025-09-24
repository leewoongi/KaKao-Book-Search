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
    suspend operator fun invoke() : List<Book> {
        return try {
            bookRepository.getBooks()
        } catch (e: UnknownHostException) {
            throw BookException.Network(e)
        } catch (e: Exception) {
            throw BookException.Unknown(e)
        }
    }
}