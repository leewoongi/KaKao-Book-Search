package com.woon.repository.book.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.woon.datasource.local.LocalBookDataSource
import com.woon.datasource.local.room.database.AppDatabase
import com.woon.datasource.local.room.entity.BookCacheEntity
import com.woon.datasource.local.room.entity.RemoteKeys
import com.woon.datasource.remote.RemoteBookDataSource
import com.woon.repository.book.mapper.toCacheEntity
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class BookRemoteMediator(
    private val query: String,
    private val filter: String,
    private val remoteDataSource: RemoteBookDataSource,
    private val localDataSource: LocalBookDataSource,
    private val db: AppDatabase
) : RemoteMediator<Int, BookCacheEntity>() {

    private val cacheDao = db.bookCacheDao()
    private val remoteKeysDao = db.remoteKeysDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, BookCacheEntity>
    ): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> {
                    STARTING_PAGE_INDEX
                }

                LoadType.PREPEND -> {
                    return MediatorResult.Success(endOfPaginationReached = true)
                }

                LoadType.APPEND -> {
                    val item = state.lastItemOrNull()
                    if (item == null) {
                        return MediatorResult.Success(endOfPaginationReached = false)
                    } else {
                        val key = remoteKeysDao.getLastRemoteKey(item.isbn)
                        val nextPage = key?.nextKey ?: return MediatorResult.Success(
                            endOfPaginationReached = true
                        )
                        nextPage
                    }
                }
            }


            val response = remoteDataSource.getBooks(
                query = query,
                filter = filter,
                page = page,
                size = state.config.pageSize
            )

            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    cacheDao.deleteAll()
                    remoteKeysDao.clearAll()
                }

                val prevKey = if (page <= STARTING_PAGE_INDEX) null else page - 1
                val nextKey = if (response.meta.isEnd) null else page + 1

                val keys = response.documents.map { doc ->
                    RemoteKeys(
                        id = doc.isbn,
                        prevKey = prevKey,
                        nextKey = nextKey,
                        currentPage = page,
                        isEnd = response.meta.isEnd
                    )
                }

                remoteKeysDao.insertAll(keys)

                val local = localDataSource.getBooksByQuery(
                    query = query
                ).map { it.isbn }.toSet()

                val new = response.documents.map { book ->
                    book.toCacheEntity(
                        query = query
                    ).copy(
                        favorite = local.contains(book.isbn)
                    )
                }

                cacheDao.insertAll(new)
            }
            MediatorResult.Success(endOfPaginationReached = response.meta.isEnd)
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        } catch (e: Exception) {
            e.printStackTrace()
            MediatorResult.Error(e)
        }
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }
}
