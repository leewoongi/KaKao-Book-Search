package com.woon.datasource.local

import androidx.paging.PagingSource
import com.woon.datasource.local.room.entity.BookCacheEntity
import com.woon.datasource.local.room.entity.BookEntity

interface LocalBookDataSource {
    fun getBooks(query: String) : PagingSource<Int, BookEntity>
    suspend fun getBooksByQuery(query: String) : List<BookEntity>

    suspend fun saveBookEntity(entity: BookEntity)
    suspend fun updateBookEntity(entity: BookEntity)
    suspend fun updateBookCacheEntity(entity: BookCacheEntity)
}