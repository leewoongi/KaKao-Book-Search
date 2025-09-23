package com.woon.home.model

data class BookUiModel(
    val authors: List<String>,
    val contents: String,
    val time: String,
    val isbn: String,
    val salePrice: String,
    val price: String,
    val salePercent: String,
    val publisher: String,
    val status: String,
    val title: String,
    val image: String,
    val translators: List<String>,
    val url: String,
    val isFavorite: Boolean = false
)
