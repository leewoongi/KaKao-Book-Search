package com.woon.datasource.local.room.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.woon.datasource.local.room.entity.BookEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {

    /** 판매 가격에 검색어가 포함된 책들을 가격 오름차순 정렬 */
    @Query("""
        SELECT * FROM books 
        WHERE title LIKE '%' || :query || '%' 
        AND salePrice >= :minPrice 
        AND salePrice <= :maxPrice 
        AND favorite = 1
        ORDER BY title ASC
    """)
    fun pagingSourceByQueryAndPriceRangeTitleAsc(
        query: String,
        minPrice: Int,
        maxPrice: Int
    ): PagingSource<Int, BookEntity>

    /** 판매 가격에 검색어가 포함된 책들을 가격 내림차순 정렬 */
    @Query("""
        SELECT * FROM books 
        WHERE title LIKE '%' || :query || '%' 
        AND salePrice >= :minPrice 
        AND salePrice <= :maxPrice
        AND favorite = 1
        ORDER BY title DESC
    """)
    fun pagingSourceByQueryAndPriceRangeTitleDesc(
        query: String,
        minPrice: Int,
        maxPrice: Int
    ): PagingSource<Int, BookEntity>

    /** 로컬에 저장된 즐겨찾기 검색어로 가져오기 */
    @Query("SELECT * FROM books WHERE `query` = :query AND favorite = 1")
    suspend fun getBooksByQuery(query: String): List<BookEntity>

    /** 즐겨찾기 추가 */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(book: BookEntity)

    /** 즐겨찾기 수정 */
    @Update
    suspend fun update(book: BookEntity)

    /** 즐겨찾기 삭제 */
    @Delete
    suspend fun delete(book: BookEntity)

    /** isbn 단일 book 가져오기 */
    @Query("SELECT * FROM books WHERE isbn = :id")
    fun getBookById(id: String): Flow<BookEntity?>

    /** 즐겨찾기가 아닌 book 삭제 */
    @Query("DELETE FROM books WHERE favorite = 0")
    suspend fun deleteNonFavoriteBooks()
}