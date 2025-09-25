package com.woon.repository.book

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.woon.datasource.local.LocalBookDataSource
import com.woon.domain.book.entity.Book
import com.woon.domain.book.repository.BookRepository
import com.woon.repository.book.mapper.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import androidx.paging.map
import com.woon.datasource.local.room.database.AppDatabase
import com.woon.datasource.remote.RemoteBookDataSource
import com.woon.domain.book.entity.SortType
import com.woon.repository.book.mapper.toCacheEntity
import com.woon.repository.book.mapper.toEntity
import com.woon.repository.book.paging.BookRemoteMediator

class BookRepositoryImpl
@Inject constructor(
    private val remoteDataSource: RemoteBookDataSource,
    private val localDataSource: LocalBookDataSource,
    private val db: AppDatabase
): BookRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getRemote(
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
                db.bookCacheDao().pagingSourceByQuery(query)
            }
        ).flow.map { pagingData ->
            pagingData.map { entity ->
                entity.toDomain()
            }
        }
    }

    override fun getLocal(
        query: String,
        filter: SortType,
        range: Pair<Int, Int>
    ): Flow<PagingData<Book>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                localDataSource.getBooks(
                    query = query,
                    filter = filter,
                    range = range
                )
            }
        ).flow.map { pagingData ->
            pagingData.map { it.toDomain() }
        }
    }

    override suspend fun save(book: Book) {
        localDataSource.saveBookEntity(book.toEntity())
        localDataSource.updateBookCacheEntity(book.toCacheEntity())
    }

    override suspend fun update(book: Book) {
        localDataSource.updateBookEntity(book.toEntity())
        localDataSource.updateBookCacheEntity(book.toCacheEntity())
    }
}