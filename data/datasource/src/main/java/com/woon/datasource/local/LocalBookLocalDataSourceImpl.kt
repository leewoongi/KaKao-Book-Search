package com.woon.datasource.local

import androidx.paging.PagingSource
import com.woon.datasource.local.room.dao.BookCacheDao
import com.woon.datasource.local.room.dao.BookDao
import com.woon.datasource.local.room.entity.BookCacheEntity
import com.woon.datasource.local.room.entity.BookEntity
import com.woon.domain.book.entity.SortType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalBookLocalDataSourceImpl
@Inject constructor(
    private val bookDao: BookDao,
    private val cacheDao: BookCacheDao
) : LocalBookDataSource {

    override fun getBooks(
        query: String,
        filter: SortType,
        range: Pair<Int, Int>
    ): PagingSource<Int, BookEntity> {
        return when (filter) {
            SortType.Ascending -> {
                bookDao.pagingSourceByQueryAndPriceRangeTitleAsc(
                    query = query,
                    minPrice = range.first,
                    maxPrice = range.second
                )
            }

            SortType.Descending -> {
                bookDao.pagingSourceByQueryAndPriceRangeTitleDesc(
                    query = query,
                    minPrice = range.first,
                    maxPrice = range.second
                )
            }
        }
    }

    override suspend fun getBooksByQuery(query: String): List<BookEntity> {
        return withContext(Dispatchers.IO) {
            bookDao.getBooksByQuery(query = query)
        }
    }

    override fun getBookById(id: String): Flow<BookEntity?> {
        return bookDao.getBookById(id = id)
    }

    override suspend fun saveBookEntity(entity: BookEntity) {
        withContext(Dispatchers.IO) {
            bookDao.insert(book = entity)
        }
    }

    override suspend fun updateBookEntity(entity: BookEntity) {
        withContext(Dispatchers.IO) {
            bookDao.update(book = entity)
        }
    }

    override suspend fun deleteBookEntity(entity: BookEntity) {
        withContext(Dispatchers.IO) {
            bookDao.delete(book = entity)
        }
    }


    override suspend fun deleteNonFavoriteBooks() {
        withContext(Dispatchers.IO) {
            bookDao.deleteNonFavoriteBooks()
        }
    }

    override suspend fun updateBookCacheEntity(entity: BookCacheEntity) {
        withContext(Dispatchers.IO) {
            cacheDao.update(entity)
        }
    }

    override fun getCacheBookById(id: String): Flow<BookCacheEntity?> {
        return cacheDao.getBookById(id)
    }
}