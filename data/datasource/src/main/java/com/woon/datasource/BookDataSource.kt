package com.woon.datasource

import com.woon.datasource.remote.book.response.book.BookResponse

interface BookDataSource {
    suspend fun getBooks(
        query: String,
        filter: String,
        page: Int,
        size: Int
    ) : BookResponse
}