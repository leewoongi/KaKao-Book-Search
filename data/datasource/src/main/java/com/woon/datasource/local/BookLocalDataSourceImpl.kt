package com.woon.datasource.local

import com.woon.datasource.BookDataSource
import com.woon.datasource.remote.book.response.book.BookResponse
import javax.inject.Inject

class BookLocalDataSourceImpl
@Inject constructor(

) : BookDataSource {
    override suspend fun getBooks(
        query: String,
        filter: String,
        page: Int,
        size: Int
    ): BookResponse {
        TODO("Not yet implemented")
    }
}