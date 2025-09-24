package com.woon.datasource.remote

import com.woon.datasource.remote.book.api.BookApi
import com.woon.datasource.remote.book.response.book.BookResponse
import kotlinx.coroutines.delay
import javax.inject.Inject

class RemoteBookDataSourceImpl
@Inject constructor(
    private val bookApi: BookApi
): RemoteBookDataSource {
    override suspend fun getRemoteBooks(
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
}