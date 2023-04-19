package com.eem.remotedata.api

import com.eem.remotedata.model.authentication.RemoteGuestToken
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.appendPathSegments

class TestApiServiceImpl(private val httpClient: HttpClient) : TestApiService {

    override suspend fun getTestText(): String {
        val response = httpClient.get("https://ktor.io/docs/")
        return response.bodyAsText()
    }

    override suspend fun getGuestToken(): RemoteGuestToken = httpClient.get {
        url {
            appendPathSegments("authentication/guest_session/new")
        }
    }.body()
}
