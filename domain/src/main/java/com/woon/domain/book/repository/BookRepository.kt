package com.woon.domain.book.repository

import androidx.paging.PagingData
import com.woon.domain.book.entity.Book
import com.woon.domain.book.entity.SortType
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    fun getRemote(
        query: String,
        filter: String,
    ): Flow<PagingData<Book>>

    fun getLocal(
        query: String,
        filter: SortType,
        range: Pair<Int, Int>
    ): Flow<PagingData<Book>>

    fun getBookById(id: String): Flow<Book>

    suspend fun update(book: Book)
    suspend fun delete(book: Book)
    suspend fun deleteNonFavoriteBooks()
}