package com.woon.datasource.module

import com.woon.datasource.BookDataSource
import com.woon.datasource.local.BookLocalDataSourceImpl
import com.woon.datasource.remote.BookRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Qualifier
import jakarta.inject.Singleton

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class RemoteDataSource

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class LocalDataSource

@Module
@InstallIn(SingletonComponent::class)
abstract class BookDataStoreModule {

    @Binds
    @Singleton
    @RemoteDataSource
    abstract fun bindBookRemoteDataStore(
        bookRemoteDataSourceImpl: BookRemoteDataSourceImpl
    ): BookDataSource

    @Binds
    @Singleton
    @LocalDataSource
    abstract fun bindBookLocalDataStore(
        bookLocalDataSourceImpl: BookLocalDataSourceImpl
    ): BookDataSource


}