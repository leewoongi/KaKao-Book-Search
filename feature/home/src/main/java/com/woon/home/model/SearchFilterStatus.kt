package com.woon.home.model

enum class SearchFilterStatus(
    val value: String
) {
    ACCURACY("accuracy"),
    LATEST("latest");

    companion object {
        fun from(value: String): SearchFilterStatus {
            return entries.find { it.value == value } ?: ACCURACY
        }
    }
}