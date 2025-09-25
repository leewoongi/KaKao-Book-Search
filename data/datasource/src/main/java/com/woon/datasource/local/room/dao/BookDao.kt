package com.woon.datasource.local.room.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.woon.datasource.local.room.entity.BookEntity

@Dao
interface BookDao {
    @Query("SELECT * FROM books")
    fun getBooks(): PagingSource<Int, BookEntity>

    @Query("SELECT * FROM books")
    suspend fun getAll(): List<BookEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(book: BookEntity)

    @Delete
    suspend fun delete(book: BookEntity)

    @Query("SELECT isbn FROM books WHERE favorite = 1")
    suspend fun getFavoriteIsbns(): List<String>
}