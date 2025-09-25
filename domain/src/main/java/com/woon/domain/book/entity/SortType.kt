package com.woon.domain.book.entity

enum class SortType(
    val value: String
) {
    Descending("descending"),
    Ascending("ascending");

    companion object {
        fun from(value: String): SortType {
            return entries.find { it.value == value } ?: Descending
        }
    }
}