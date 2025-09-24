package com.woon.datasource.remote

import com.woon.datasource.BookRemoteDataStore
import com.woon.datasource.remote.book.api.BookApi
import com.woon.datasource.remote.book.response.book.BookResponse
import javax.inject.Inject

class BookRemoteDataStoreImpl
@Inject constructor(
    private val bookApi: BookApi
): BookRemoteDataStore {
    override suspend fun getBooks(
        query: String
    ) : BookResponse {
        println("TEST TEST TEST getBook")
        return bookApi.getBooks(
            query
        )
    }
}