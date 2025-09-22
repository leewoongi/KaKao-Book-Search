package com.woon.domain.book.repository

import com.woon.domain.book.entity.Book

interface BookRepository {
    suspend fun getBooks() : List<Book>
}