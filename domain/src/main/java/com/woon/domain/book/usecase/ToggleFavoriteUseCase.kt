package com.woon.domain.book.usecase

import com.woon.domain.book.entity.Book
import com.woon.domain.book.repository.BookRepository
import javax.inject.Inject

class ToggleFavoriteUseCase
@Inject constructor(
    private val bookRepository: BookRepository
){
    suspend operator fun invoke(book: Book) {
        bookRepository.save(book)
    }
}