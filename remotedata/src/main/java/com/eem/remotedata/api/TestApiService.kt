package com.eem.remotedata.api

import com.eem.remotedata.model.authentication.RemoteGuestToken
import com.eem.remotedata.model.authentication.RemoteRequestToken

interface TestApiService {

    suspend fun getTestText(): String
    suspend fun getGuestToken(): RemoteGuestToken
    suspend fun getRequestToken(): RemoteRequestToken
}
