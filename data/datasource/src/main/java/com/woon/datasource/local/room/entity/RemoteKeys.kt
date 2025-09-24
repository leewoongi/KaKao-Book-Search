package com.woon.datasource.local.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKeys(
    @PrimaryKey
    val id: String, // "query#isbn"
    val query: String,
    val prevKey: Int?,
    val nextKey: Int?,
    val currentPage: Int
)