package com.woon.domain.book.usecase

import com.woon.domain.book.exception.BookException
import com.woon.domain.book.repository.BookRepository
import javax.inject.Inject

class RemoveFavoriteBookUseCase
@Inject constructor(
    private val bookRepository: BookRepository
){
    suspend operator fun invoke(){
        try {
            bookRepository.deleteNonFavoriteBooks()
        } catch (e: Exception) {
            throw BookException.RemoveFailure(e)
        }
    }
}