package com.woon.datasource.module

import com.woon.datasource.BookRemoteDataStore
import com.woon.datasource.remote.BookRemoteDataStoreImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class BookDataStoreModule {
    @Binds
    abstract fun bindBookRemoteDataStore(
        bookRemoteDataStoreImpl: BookRemoteDataStoreImpl
    ): BookRemoteDataStore

}