package com.woon.repository

import com.woon.datasource.BookRemoteDataStore
import com.woon.domain.repository.BookRepository
import javax.inject.Inject

class BookRepositoryImpl
@Inject constructor(
    private val bookRemoteDataStore: BookRemoteDataStore
): BookRepository{
    override suspend fun getBooks() {
        val result = bookRemoteDataStore.getBooks()
        println("TEST TEST TEST result: $result")
    }
}