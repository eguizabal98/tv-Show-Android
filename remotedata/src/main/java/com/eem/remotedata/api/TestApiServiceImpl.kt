package com.eem.remotedata.api

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText

class TestApiServiceImpl(private val httpClient: HttpClient) : TestApiService {

    override suspend fun getTestText(): String {
        val response = httpClient.get("https://ktor.io/docs/")
        return response.bodyAsText()
    }
}
