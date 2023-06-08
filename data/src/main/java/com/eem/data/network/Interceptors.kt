package com.eem.data.network

import com.eem.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor

private const val API_KEY_QUERY = "api_key"

fun getApiKeyInterceptor() = Interceptor { chain ->
    val original = chain.request()
    val originalHttpUrl = original.url

    val url = originalHttpUrl.newBuilder()
        .addQueryParameter(API_KEY_QUERY, BuildConfig.API_KEY)
        .build()

    val requestBuilder = original.newBuilder()
        .url(url)

    val request = requestBuilder.build()
    chain.proceed(request)
}

fun getLoggerInterceptor() = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}
