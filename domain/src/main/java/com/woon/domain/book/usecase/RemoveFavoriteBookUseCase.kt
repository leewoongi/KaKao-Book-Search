package com.woon.domain.book.usecase

import com.woon.domain.book.entity.Book
import com.woon.domain.book.repository.BookRepository
import javax.inject.Inject

class RemoveFavoriteBookUseCase
@Inject constructor(
    private val bookRepository: BookRepository
){
    suspend operator fun invoke(){
        bookRepository.deleteNonFavoriteBooks()
    }
}