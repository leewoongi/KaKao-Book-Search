package com.woon.domain.book.entity

enum class BookStatus(
    val status: String
) {
    AVAILABLE("정상판매"),
    OUT_OF_STOCK("품절"),
    OUT_OF_PRINT("절판"),
    NONE("알 수 없음");

    companion object {
        fun from(status: String): BookStatus {
            return entries.find { it.status == status } ?: NONE
        }
    }
}