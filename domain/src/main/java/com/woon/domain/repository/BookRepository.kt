package com.woon.domain.repository

interface BookRepository {
    suspend fun getBooks()
}