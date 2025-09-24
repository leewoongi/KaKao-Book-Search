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
import java.util.concurrent.TimeUnit

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
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        return MediatorResult.Success(endOfPaginationReached = false)
                    }

                    val remoteKeys = remoteKeysDao.getRemoteKeys("$query#${lastItem.isbn}")
                    val nextPage = remoteKeys?.nextKey

                    if (nextPage == null) {
                        return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    }
                    nextPage
                }
            }


            val response = remoteDataSource.getRemoteBooks(
                query = query,
                filter = filter,
                page = page,
                size = state.config.pageSize
            )

            val endOfPaginationReached = response.meta.isEnd

            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    cacheDao.clearByQuery(query)
                    remoteKeysDao.clearByQuery(query)
                }

                val prevKey = if (page <= STARTING_PAGE_INDEX) null else page - 1
                val nextKey = if (endOfPaginationReached) null else page + 1

                val keys = response.documents.map { doc ->
                    RemoteKeys(
                        id = "$query#${doc.isbn}",
                        query = query,
                        prevKey = prevKey,
                        nextKey = nextKey,
                        currentPage = page
                    )
                }
                remoteKeysDao.insertAll(keys)

                val favoriteIsbns = localDataSource.getFavoriteIsbns()
                val startPosition = if (loadType == LoadType.REFRESH) 0
                else ((page - 1) * state.config.pageSize)

                val entities = response.documents.mapIndexed { index, doc ->
                    doc.toCacheEntity(
                        query,
                        "$query#${doc.isbn}",
                        page = page,
                        position = startPosition + index
                    ).copy(
                        favorite = favoriteIsbns.contains(doc.isbn),
                    )
                }
                cacheDao.insertAll(entities)
            }

            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)

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
