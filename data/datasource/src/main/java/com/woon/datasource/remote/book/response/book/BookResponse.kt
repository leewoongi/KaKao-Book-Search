package com.woon.datasource.remote.book.response.book

import com.google.gson.annotations.SerializedName


data class BookResponse(
    @SerializedName("meta") val meta: Meta,
    @SerializedName("documents") val documents: List<Document>
)
