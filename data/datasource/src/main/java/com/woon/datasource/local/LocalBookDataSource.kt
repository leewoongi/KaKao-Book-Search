package com.woon.datasource.local

import androidx.paging.PagingSource
import com.woon.datasource.local.room.entity.BookCacheEntity
import com.woon.datasource.local.room.entity.BookEntity
import com.woon.domain.book.entity.SortType
import kotlinx.coroutines.flow.Flow

interface LocalBookDataSource {
    fun getBooks(
        query: String,
        filter: SortType,
        range: Pair<Int, Int>
    ) : PagingSource<Int, BookEntity>
    suspend fun getBooksByQuery(query: String) : List<BookEntity>
    fun getBookById(id: String) : Flow<BookEntity?>

    suspend fun updateBookEntity(entity: BookEntity)
    suspend fun deleteBookEntity(entity: BookEntity)
    suspend fun deleteNonFavoriteBooks()

    fun getCacheBookById(id: String) : Flow<BookCacheEntity?>
    suspend fun updateBookCacheEntity(entity: BookCacheEntity)

}