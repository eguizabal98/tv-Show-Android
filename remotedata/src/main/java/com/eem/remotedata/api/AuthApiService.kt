package com.eem.remotedata.api

import com.eem.remotedata.model.authentication.RemoteGuestToken
import com.eem.remotedata.model.authentication.RemoteRequestToken
import com.eem.remotedata.model.authentication.RemoteSessionId
import com.eem.remotedata.model.authentication.RemoteSessionIdRequest

interface AuthApiService {
    suspend fun getGuestToken(): RemoteGuestToken
    suspend fun getRequestToken(): RemoteRequestToken
    suspend fun createSession(remoteSessionIdRequest: RemoteSessionIdRequest): RemoteSessionId
}
