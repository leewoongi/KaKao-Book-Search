package com.woon.datasource.module

import com.woon.datasource.local.LocalBookDataSource
import com.woon.datasource.local.LocalBookLocalDataSourceImpl
import com.woon.datasource.remote.RemoteBookDataSource
import com.woon.datasource.remote.RemoteBookDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Qualifier
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class BookDataStoreModule {

    @Binds
    @Singleton
    abstract fun bindBookRemoteDataStore(
        remoteBookDataSourceImpl: RemoteBookDataSourceImpl
    ): RemoteBookDataSource

    @Binds
    @Singleton
    abstract fun bindBookLocalDataStore(
        bookLocalDataSourceImpl: LocalBookLocalDataSourceImpl
    ): LocalBookDataSource
}