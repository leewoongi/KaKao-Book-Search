package com.woon.datasource.local.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.woon.datasource.local.room.dao.BookDao
import com.woon.datasource.local.room.entity.BookEntity

@Database(
    entities = [BookEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
}