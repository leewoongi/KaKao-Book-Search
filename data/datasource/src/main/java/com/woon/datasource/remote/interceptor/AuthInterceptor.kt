package com.woon.datasource.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Response

//todo key 관리 변경
class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val request = original.newBuilder()
            .header("Authorization", "KakaoAK 126abb8ae9b48f1b45700d586e4d524b")
            .build()
        return chain.proceed(request)
    }
}