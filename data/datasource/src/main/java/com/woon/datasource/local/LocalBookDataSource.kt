package com.woon.datasource.local

import androidx.paging.PagingSource
import com.woon.datasource.local.room.entity.BookEntity
import com.woon.datasource.remote.book.response.book.BookResponse

interface LocalBookDataSource {
    fun getBooks() : PagingSource<Int, BookEntity>
    suspend fun getFavoriteBooks() : List<BookEntity>

    suspend fun saveFavoriteBook(entity: BookEntity)
    suspend fun deleteFavoriteBook(entity: BookEntity)
    suspend fun getFavoriteIsbns(): Set<String>

}