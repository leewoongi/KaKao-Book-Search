package com.woon.datasource.local

import androidx.paging.PagingSource
import com.woon.datasource.local.room.dao.BookDao
import com.woon.datasource.local.room.entity.BookEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalBookLocalDataSourceImpl
@Inject constructor(
    private val bookDao: BookDao
) : LocalBookDataSource {
    override fun getBooks(): PagingSource<Int, BookEntity> {
        return bookDao.getBooks()
    }

    override suspend fun getFavoriteBooks(): List<BookEntity> {
        return bookDao.getFavoriteBooks()
    }

    override suspend fun saveFavoriteBook(entity: BookEntity) {
        withContext(Dispatchers.IO) {
            bookDao.insertBook(book = entity)
        }
    }

    override suspend fun deleteFavoriteBook(entity: BookEntity) {
        withContext(Dispatchers.IO) {
            bookDao.deleteBook(book = entity)
        }
    }

    override suspend fun getFavoriteIsbns(): Set<String> {
        return bookDao.getFavoriteIsbns().toSet()
    }
}