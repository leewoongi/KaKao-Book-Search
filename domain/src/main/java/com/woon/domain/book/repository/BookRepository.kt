package com.woon.domain.book.repository

import com.woon.domain.book.entity.Book

interface BookRepository {
    suspend fun getBooks(query: String) : List<Book>
}