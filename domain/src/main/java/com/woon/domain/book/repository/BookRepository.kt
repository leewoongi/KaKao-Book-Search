package com.woon.domain.book.repository

import androidx.paging.PagingData
import com.woon.domain.book.entity.Book
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    fun getRemoteBooks(
        query: String,
        filter: String,
    ): Flow<PagingData<Book>>

    fun getLocalBooks(
        query: String,
        filter: String,
    ): Flow<PagingData<Book>>

    suspend fun saveFavoriteBook(book: Book)
    suspend fun deleteFavoriteBook(book: Book)
}