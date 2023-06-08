package com.eem.data.network.utils

import com.eem.data.BuildConfig
import okhttp3.CertificatePinner
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun <Api> buildApi(
    certificatePinner: CertificatePinner,
    interceptors: List<Interceptor> = emptyList(),
    api: Class<Api>
): Api {
    val client = OkHttpClient.Builder()
    client.certificatePinner(certificatePinner)
    interceptors.forEach {
        client.addInterceptor(it)
    }
    return Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(client.build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(api)
}
