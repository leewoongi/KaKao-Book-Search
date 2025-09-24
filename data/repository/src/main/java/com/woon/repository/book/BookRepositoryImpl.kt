package com.woon.repository.book

import androidx.paging.ExperimentalPagingApi
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
import com.woon.datasource.local.room.database.AppDatabase
import com.woon.datasource.remote.RemoteBookDataSource
import com.woon.repository.book.mapper.toEntity
import com.woon.repository.book.paging.BookRemoteMediator

class BookRepositoryImpl
@Inject constructor(
    private val remoteDataSource: RemoteBookDataSource,
    private val localDataSource: LocalBookDataSource,
    private val db: AppDatabase
): BookRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getRemoteBooks(
        query: String,
        filter: String,
    ): Flow<PagingData<Book>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
                prefetchDistance = 5,
                initialLoadSize = 40
            ),
            remoteMediator = BookRemoteMediator(
                query = query,
                filter = filter,
                remoteDataSource = remoteDataSource,
                localDataSource = localDataSource,
                db = db
            ),
            pagingSourceFactory = {
                db.bookCacheDao().pagingSource(query)
            }
        ).flow.map { pagingData ->
            pagingData.map { entity ->
                entity.toDomain()
            }
        }
    }

    override fun getLocalBooks(
        query: String,
        filter: String
    ): Flow<PagingData<Book>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { localDataSource.getBooks() }
        ).flow.map { pagingData ->
            pagingData.map { it.toDomain() }
        }
    }

    override suspend fun saveFavoriteBook(book: Book) {
        localDataSource.saveFavoriteBook(book.toEntity())
    }

    override suspend fun deleteFavoriteBook(book: Book) {
        localDataSource.deleteFavoriteBook(book.toEntity())
    }
}