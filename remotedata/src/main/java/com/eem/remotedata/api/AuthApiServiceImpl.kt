package com.eem.remotedata.api

import com.eem.remotedata.model.authentication.RemoteGuestToken
import com.eem.remotedata.model.authentication.RemoteRequestToken
import com.eem.remotedata.model.authentication.RemoteSessionId
import com.eem.remotedata.model.authentication.RemoteSessionIdRequest
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.appendPathSegments
import io.ktor.http.contentType

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

    override suspend fun createSession(remoteSessionIdRequest: RemoteSessionIdRequest): RemoteSessionId =
        httpClient.post {
            url {
                appendPathSegments("authentication/session/new")
            }
            contentType(ContentType.Application.Json)
            setBody(remoteSessionIdRequest)
        }.body()
}
