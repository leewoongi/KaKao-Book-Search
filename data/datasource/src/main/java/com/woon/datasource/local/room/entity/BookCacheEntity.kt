package com.woon.datasource.local.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books_cache")
data class BookCacheEntity(
    @PrimaryKey
    val id: String, // "query#isbn" 형태의 복합키
    val isbn: String,
    val title: String,
    val authors: String,
    val contents: String,
    val time: Long,
    val price: Double,
    val salePrice: Double,
    val publisher: String,
    val status: String,
    val image: String,
    val translators: String,
    val url: String,
    val favorite: Boolean,
    val query: String,
    val page: Int,
    val position: Int
)