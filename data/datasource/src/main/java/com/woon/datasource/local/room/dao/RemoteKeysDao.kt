package com.woon.datasource.local.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.woon.datasource.local.room.entity.RemoteKeys

@Dao
interface RemoteKeysDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(keys: List<RemoteKeys>)

    @Query("SELECT * FROM remote_keys WHERE id = :id ORDER BY currentPage DESC LIMIT 1")
    suspend fun getLastRemoteKey(id:String): RemoteKeys?

    /** 검색어 바뀌거나 첫 로드시 전에 검색한 내용 전부 삭제*/
    @Query("DELETE FROM remote_keys")
    suspend fun clearAll()
}