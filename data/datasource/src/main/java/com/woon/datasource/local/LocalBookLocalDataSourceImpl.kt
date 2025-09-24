package com.woon.datasource.local

import com.woon.datasource.local.LocalBookDataSource
import com.woon.datasource.local.room.dao.BookDao
import com.woon.datasource.local.room.entity.BookEntity
import com.woon.datasource.remote.book.response.book.BookResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LocalBookLocalDataSourceImpl
@Inject constructor(
    private val bookDao: BookDao
) : LocalBookDataSource {

    override suspend fun getFavoriteBooks(): List<BookEntity> {
        TODO("Not yet implemented")
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
}