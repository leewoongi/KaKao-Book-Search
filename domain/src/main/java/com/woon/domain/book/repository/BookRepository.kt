package com.woon.domain.book.repository

import androidx.paging.PagingData
import com.woon.domain.book.entity.Book
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    fun getRemote(
        query: String,
        filter: String,
    ): Flow<PagingData<Book>>

    fun getLocal(
        query: String,
        filter: String,
    ): Flow<PagingData<Book>>

    suspend fun save(book: Book)
    suspend fun update(book: Book)
}