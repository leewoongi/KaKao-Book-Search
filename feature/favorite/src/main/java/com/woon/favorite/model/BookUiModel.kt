package com.woon.favorite.model

data class BookUiModel(
    val authors: String,
    val contents: String,
    val time: String,
    val isbn: String,
    val salePrice: String,
    val price: String,
    val salePercent: String,
    val isDiscount: Boolean,
    val publisher: String,
    val status: String,
    val title: String,
    val image: String,
    val translators: List<String>,
    val url: String,
    val isFavorite: Boolean,
    val query: String
)
