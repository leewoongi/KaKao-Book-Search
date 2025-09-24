package com.woon.datasource.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.woon.datasource.local.room.entity.RemoteKeys

@Dao
interface RemoteKeysDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(keys: List<RemoteKeys>)

    @Query("SELECT * FROM remote_keys WHERE id = :id")
    suspend fun getRemoteKeys(id: String): RemoteKeys?

    @Query("DELETE FROM remote_keys WHERE `query` = :query")
    suspend fun clearByQuery(query: String)

    @Query("SELECT * FROM remote_keys WHERE `query` = :query ORDER BY currentPage DESC LIMIT 1")
    suspend fun getLastRemoteKey(query: String): RemoteKeys?
}