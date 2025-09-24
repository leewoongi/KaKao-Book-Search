package com.woon.datasource.local.room.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.woon.datasource.local.room.entity.BookEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Query("SELECT * FROM books")
    fun getBooks(): PagingSource<Int, BookEntity>

    @Query("SELECT * FROM books WHERE favorite = 1")
    suspend fun getFavoriteBooks(): List<BookEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBook(book: BookEntity)

    @Delete
    suspend fun deleteBook(book: BookEntity)

    @Query("SELECT isbn FROM books WHERE favorite = 1")
    suspend fun getFavoriteIsbns(): List<String>
}