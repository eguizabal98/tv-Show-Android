package com.eem.data.network.api

import com.eem.data.network.model.authentication.RemoteGuestToken
import com.eem.data.network.model.authentication.RemoteRequestToken
import com.eem.data.network.model.authentication.RemoteSessionId
import com.eem.data.network.model.authentication.RemoteSessionIdRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApiService {

    @GET("authentication/guest_session/new")
    suspend fun getGuestToken(): Response<RemoteGuestToken>

    @GET("authentication/token/new")
    suspend fun getRequestToken(): Response<RemoteRequestToken>

    @POST("authentication/session/new")
    suspend fun createSession(
        @Body remoteSessionIdRequest: RemoteSessionIdRequest
    ): Response<RemoteSessionId>
}
