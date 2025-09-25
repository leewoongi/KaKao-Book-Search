package com.woon.domain.book.entity

import com.woon.domain.money.entity.Money
import java.util.Date

/**
 * 책 정보
 * @param authors 저자
 * @param contents 책 소개
 * @param time 출판일
 * @param isbn ISBN
 * @param salePrice 판매 가격
 * @param price 정가
 * @param publisher 출판사
 * @param status 판매 상태
 * @param title 책 제목
 * @param image 책 표지
 * @param translators 번역가
 * @param url 책 상세 페이지
 * @param isFavorite 즐겨찾기 여부
 * @param query 검색어
 */
data class Book(
    val authors: List<String>,
    val contents: String,
    val time: Date,
    val isbn: String,
    val salePrice: Money,
    val price: Money,
    val publisher: String,
    val status: BookStatus,
    val title: String,
    val image: String,
    val translators: List<String>,
    val url: String,
    val isFavorite: Boolean,
    val query: String
){
    fun convertAuthorsToString(): String {
        return authors.joinToString(", ")
    }

    fun isDisCount() : Boolean {
        return price.changeRateString(salePrice) != "0"
    }
}
