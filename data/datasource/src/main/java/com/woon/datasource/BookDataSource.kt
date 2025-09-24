package com.woon.datasource

import com.woon.datasource.local.room.entity.BookEntity
import com.woon.datasource.remote.book.response.book.BookResponse
import com.woon.domain.book.entity.Book

interface BookDataSource {
    suspend fun getBooks(
        query: String,
        filter: String,
        page: Int,
        size: Int
    ) : BookResponse

    suspend fun saveFavoriteBook(entity: BookEntity)
}