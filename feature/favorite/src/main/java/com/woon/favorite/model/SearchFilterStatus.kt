package com.woon.favorite.model

enum class SearchFilterStatus(
    val value: String
) {
    Descending("descending"),
    Ascending("ascending"),
    Price("price");

    companion object {
        fun from(value: String): SearchFilterStatus {
            return SearchFilterStatus.entries.find { it.value == value } ?: Descending
        }
    }
}