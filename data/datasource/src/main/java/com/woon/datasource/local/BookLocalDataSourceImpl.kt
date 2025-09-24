package com.woon.datasource.local

import com.woon.datasource.BookDataSource
import com.woon.datasource.local.room.dao.BookDao
import com.woon.datasource.local.room.entity.BookEntity
import com.woon.datasource.remote.book.response.book.BookResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BookLocalDataSourceImpl
@Inject constructor(
    private val bookDao: BookDao
) : BookDataSource {
    override suspend fun getBooks(
        query: String,
        filter: String,
        page: Int,
        size: Int
    ): BookResponse {
        TODO("Not yet implemented")
    }

    override suspend fun saveFavoriteBook(entity: BookEntity) {
        withContext(Dispatchers.IO) {
            bookDao.insertBook(book = entity)
        }
    }
}