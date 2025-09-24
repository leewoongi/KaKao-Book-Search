package com.woon.datasource.local

import com.woon.datasource.local.room.entity.BookEntity
import com.woon.datasource.remote.book.response.book.BookResponse

interface LocalBookDataSource {
    suspend fun getFavoriteBooks() : List<BookEntity>

    suspend fun saveFavoriteBook(entity: BookEntity)
    suspend fun deleteFavoriteBook(entity: BookEntity)
}