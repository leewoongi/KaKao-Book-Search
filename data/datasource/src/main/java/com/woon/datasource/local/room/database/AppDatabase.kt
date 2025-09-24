package com.woon.datasource.local.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.woon.datasource.local.room.dao.BookCacheDao
import com.woon.datasource.local.room.dao.BookDao
import com.woon.datasource.local.room.dao.RemoteKeysDao
import com.woon.datasource.local.room.entity.BookCacheEntity
import com.woon.datasource.local.room.entity.BookEntity
import com.woon.datasource.local.room.entity.RemoteKeys

@Database(
    entities = [
        BookEntity::class,
        BookCacheEntity::class,
        RemoteKeys::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
    abstract fun bookCacheDao(): BookCacheDao
    abstract fun remoteKeysDao(): RemoteKeysDao
}