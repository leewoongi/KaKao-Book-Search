package com.woon.domain.usecase

import com.woon.domain.repository.BookRepository
import javax.inject.Inject

class GetBooksUseCase
@Inject constructor(
    private val bookRepository: BookRepository
){
    suspend operator fun invoke(){
        return bookRepository.getBooks()
    }
}