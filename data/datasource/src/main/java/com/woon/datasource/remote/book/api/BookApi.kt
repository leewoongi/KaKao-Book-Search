package com.woon.datasource.remote.book.api

import com.woon.datasource.remote.book.response.book.BookResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BookApi {

    /**
     * 책 검색
     * @param query 검색을 원하는 질의어
     * @param sort 결과 문서 정렬 방식 (기본 값 accuracy(정확도순) 또는 recency(발간일순))
     * @param page 결과 페이지 번호 (1~50) 기본값 1
     * @param size 한 페이지에 보여질 문서 수 (1~50) 기본값 10
     */
    @GET("book")
    suspend fun getBooks(
        @Query("query") query: String,
        @Query("sort") sort: String? = "accuracy",
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 10,
    ): BookResponse
}