package com.eem.remotedata.api

import com.eem.remotedata.model.authentication.RemoteGuestToken
import com.eem.remotedata.model.authentication.RemoteRequestToken
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.appendPathSegments

class AuthApiServiceImpl(private val httpClient: HttpClient) : AuthApiService {

    override suspend fun getGuestToken(): RemoteGuestToken = httpClient.get {
        url {
            appendPathSegments("authentication/guest_session/new")
        }
    }.body()

    override suspend fun getRequestToken(): RemoteRequestToken = httpClient.get {
        url {
            appendPathSegments("authentication/token/new")
        }
    }.body()
}
