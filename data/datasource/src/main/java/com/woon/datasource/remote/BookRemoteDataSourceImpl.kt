package com.woon.datasource.remote

import com.woon.datasource.BookDataSource
import com.woon.datasource.local.room.entity.BookEntity
import com.woon.datasource.remote.book.api.BookApi
import com.woon.datasource.remote.book.response.book.BookResponse
import com.woon.domain.book.entity.Book
import kotlinx.coroutines.delay
import javax.inject.Inject

class BookRemoteDataSourceImpl
@Inject constructor(
    private val bookApi: BookApi
): BookDataSource {
    override suspend fun getBooks(
        query: String,
        filter: String,
        page: Int,
        size: Int
    ) : BookResponse {
        delay(1000)
        return bookApi.getBooks(
            query = query,
            sort = filter,
            page = page,
            size = size
        )
    }

    override suspend fun saveFavoriteBook(entity: BookEntity) {
        // Local에서만 사용
    }
}