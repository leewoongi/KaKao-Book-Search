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
    /** 로컬에 저장된 즐겨찾기 검색어로 페이징 가져오기 */
    @Query("SELECT * FROM books WHERE `query` = :query")
    fun pagingSourceByQuery(query: String): PagingSource<Int, BookEntity>

    /** 로컬에 저장된 즐겨찾기 전부 가져오기 */
    @Query("SELECT * FROM books")
    fun pagingSourceAll(): PagingSource<Int, BookEntity>

    /** 로컬에 저장된 즐겨찾기 검색어로 가져오기 */
    @Query("SELECT * FROM books WHERE `query` = :query")
    suspend fun getBooksByQuery(query: String): List<BookEntity>

    /** 즐겨찾기 추가 */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(book: BookEntity)

    /** 즐겨찾기 삭제 */
    @Delete
    suspend fun delete(book: BookEntity)
}