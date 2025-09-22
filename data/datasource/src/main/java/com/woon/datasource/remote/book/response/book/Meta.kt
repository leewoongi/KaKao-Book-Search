package com.woon.datasource.remote.book.response.book

import com.google.gson.annotations.SerializedName

/**
 * @param isEnd 마지막 페이지 여부
 * @param pageableCount 전체 페이지의 개수
 * @param totalCount 검색된 문서 수
 */
data class Meta(
    @SerializedName("is_end") val isEnd: Boolean,
    @SerializedName("pageable_count") val pageableCount: Int,
    @SerializedName("total_count") val totalCount: Int
)
