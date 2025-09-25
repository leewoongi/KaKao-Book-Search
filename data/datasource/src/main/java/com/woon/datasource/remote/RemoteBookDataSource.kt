package com.woon.datasource.remote

import com.woon.datasource.remote.book.response.book.BookResponse

interface RemoteBookDataSource {
    suspend fun getBooks(
        query: String,
        filter: String,
        page: Int,
        size: Int
    ) : BookResponse
}