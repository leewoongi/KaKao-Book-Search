package com.woon.datasource.local

import androidx.paging.PagingSource
import com.woon.datasource.local.room.entity.BookEntity
import com.woon.datasource.remote.book.response.book.BookResponse

interface LocalBookDataSource {
    fun getFavoriteBooks() : PagingSource<Int, BookEntity>

    suspend fun saveFavoriteBook(entity: BookEntity)
    suspend fun deleteFavoriteBook(entity: BookEntity)
}