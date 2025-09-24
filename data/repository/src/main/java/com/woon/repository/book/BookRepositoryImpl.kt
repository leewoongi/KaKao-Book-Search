package com.woon.repository.book

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.woon.datasource.local.LocalBookDataSource
import com.woon.domain.book.entity.Book
import com.woon.domain.book.repository.BookRepository
import com.woon.repository.book.mapper.toDomain
import com.woon.repository.book.paging.BookPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import androidx.paging.map
import com.woon.datasource.remote.RemoteBookDataSource
import com.woon.repository.book.mapper.toEntity

class BookRepositoryImpl
@Inject constructor(
    private val remoteBookDataSource: RemoteBookDataSource,
    private val localBookDataSource: LocalBookDataSource
): BookRepository {
    override fun getBookList(
        query: String,
        filter: String,
    ): Flow<PagingData<Book>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                BookPagingSource(remoteBookDataSource, query, filter)
            }
        ).flow.map { pagingData ->
            pagingData.map { it.toDomain() }
        }
    }

    override suspend fun saveFavoriteBook(book: Book) {
        localBookDataSource.saveFavoriteBook(book.toEntity())
    }

    override suspend fun deleteFavoriteBook(book: Book) {
        localBookDataSource.deleteFavoriteBook(book.toEntity())
    }
}