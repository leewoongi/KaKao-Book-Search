package com.woon.datasource.local

import androidx.paging.PagingSource
import com.woon.datasource.local.room.entity.BookCacheEntity
import com.woon.datasource.local.room.entity.BookEntity
import com.woon.domain.book.entity.SortType

interface LocalBookDataSource {
    fun getBooks(query: String, filter: SortType) : PagingSource<Int, BookEntity>
    suspend fun getBooksByQuery(query: String) : List<BookEntity>

    suspend fun saveBookEntity(entity: BookEntity)
    suspend fun updateBookEntity(entity: BookEntity)
    suspend fun updateBookCacheEntity(entity: BookCacheEntity)
}