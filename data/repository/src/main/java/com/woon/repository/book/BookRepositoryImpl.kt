package com.woon.repository.book

import com.woon.datasource.BookRemoteDataStore
import com.woon.domain.book.entity.Book
import com.woon.domain.book.repository.BookRepository
import com.woon.repository.book.mapper.toDomain
import javax.inject.Inject

class BookRepositoryImpl
@Inject constructor(
    private val bookRemoteDataStore: BookRemoteDataStore
): BookRepository {
    override suspend fun getBooks(query: String) : List<Book> {
        val result = bookRemoteDataStore.getBooks(query).documents.map {
            it.toDomain()
        }
        return result
    }
}