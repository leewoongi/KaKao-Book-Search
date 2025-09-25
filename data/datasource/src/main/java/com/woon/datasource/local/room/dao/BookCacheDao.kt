package com.woon.datasource.local.room.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.woon.datasource.local.room.entity.BookCacheEntity

@Dao
interface BookCacheDao {
    /** query로 캐쉬된 Book 목록 가져오기 */
    @Query("SELECT * FROM books_cache WHERE `query` = :query")
    fun pagingSourceByQuery(query: String): PagingSource<Int, BookCacheEntity>

    /** 캐시 데이터 업데이트 */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(books: List<BookCacheEntity>)


    /** 단일 캐쉬된 Book 업데이트 */
    @Update
    suspend fun update(bookCacheEntity: BookCacheEntity)

    /** 검색어 바뀌거나 첫 로드시 전에 검색한 내용 전부 삭제*/
    @Query("DELETE FROM books_cache")
    suspend fun deleteAll()
}