package com.woon.datasource.local.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 책 정보
 * @param isbn ISBN
 * @param title 책 제목
 * @param authors 저자
 * @param contents 책 소개
 * @param time 출판일
 * @param price 정가
 * @param salePrice 판매 가격
 * @param publisher 출판사
 * @param status 판매 상태
 * @param image 책 표지
 * @param translators 번역가
 * @param url 책 상세 페이지
 * @param favorite 즐겨찾기 여부
 */
@Entity(tableName = "books")
data class BookEntity(
    @PrimaryKey val isbn: String,
    val title: String,
    val authors: String,
    val contents: String,
    val time: Long,
    val price: Int,
    val salePrice: Int,
    val publisher: String,
    val status: String,
    val image: String,
    val translators: String,
    val url: String,
    val favorite: Boolean,
)
