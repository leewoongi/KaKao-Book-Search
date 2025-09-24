package com.woon.datasource

import com.woon.datasource.remote.book.response.book.BookResponse

interface BookRemoteDataStore {
    suspend fun getBooks(
        query: String,
        filter: String
    ) : BookResponse
}