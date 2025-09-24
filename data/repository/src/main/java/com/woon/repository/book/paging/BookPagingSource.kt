package com.woon.repository.book.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.woon.datasource.local.LocalBookDataSource
import com.woon.datasource.remote.RemoteBookDataSource
import com.woon.datasource.remote.book.response.book.Document

class BookPagingSource(
    private val remoteDataStore: RemoteBookDataSource,
    private val query: String,
    private val filter: String
) : PagingSource<Int, Document>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Document> {
        return try {
            val page = params.key ?: 1
            val size = params.loadSize.takeIf { it <= 20 } ?: 20

            val response = remoteDataStore.getRemoteBooks(query, filter, page, size)
            val documents = response.documents

            val nextKey = when {
                response.meta.isEnd -> null
                documents.isEmpty() -> null
                else -> page + 1
            }

            LoadResult.Page(
                data = documents,
                prevKey = if (page == 1) null else page - 1,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Document>): Int? {
        return state.anchorPosition?.let { anchor ->
            state.closestPageToPosition(anchor)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchor)?.nextKey?.minus(1)
        }
    }
}
