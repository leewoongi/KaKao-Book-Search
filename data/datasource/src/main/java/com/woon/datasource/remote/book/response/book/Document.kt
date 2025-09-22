package com.woon.datasource.remote.book.response.book

import com.google.gson.annotations.SerializedName

/**
 * Book
 * @param authors 작가
 * @param contents 소개
 * @param datetime 출판일
 * @param isbn 10자리 또는 13자리 형식의 국제 표준 도서번호 ISBN10 또는 ISBN13 중 하나 이상 포함
 * 두 값이 모두 제공될 경우 공백(' ')으로 구분
 * @param price 정가
 * @param publisher 출판사
 * @param salePrice 판매가
 * @param status 도서상태
 * @param thumbnail 썸네일 이미지 URL
 * @param title 도서 제목
 * @param translators 번역가
 * @param url 도서 상세 URL
 */
data class Document(
    @SerializedName("authors") val authors: List<String>,
    @SerializedName("contents") val contents: String,
    @SerializedName("datetime") val datetime: String,
    @SerializedName("isbn") val isbn: String,
    @SerializedName("price") val price: Int,
    @SerializedName("publisher") val publisher: String,
    @SerializedName("sale_price") val salePrice: Int,
    @SerializedName("status") val status: String,
    @SerializedName("thumbnail") val thumbnail: String,
    @SerializedName("title") val title: String,
    @SerializedName("translators") val translators: List<String>,
    @SerializedName("url") val url: String
)