package com.woon.favorite.model

enum class SearchFilterStatus(
    val value: String
) {

    DESCENDING("descending"),
    ASCENDING("ascending");

    companion object {
        fun from(value: String): SearchFilterStatus {
            return SearchFilterStatus.entries.find { it.value == value } ?: DESCENDING
        }
    }
}