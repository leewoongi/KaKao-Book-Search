package com.woon.datasource.local.room.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.woon.datasource.local.room.entity.BookCacheEntity

@Dao
interface BookCacheDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(books: List<BookCacheEntity>)

    @Query("SELECT * FROM books_cache WHERE `query` = :query ORDER BY position ASC")
    fun pagingSource(query: String): PagingSource<Int, BookCacheEntity>

    @Query("DELETE FROM books_cache WHERE `query` = :query")
    suspend fun clearByQuery(query: String)

    @Query("UPDATE books_cache SET favorite = :favorite WHERE isbn = :isbn")
    suspend fun updateFavorite(isbn: String, favorite: Boolean)
}