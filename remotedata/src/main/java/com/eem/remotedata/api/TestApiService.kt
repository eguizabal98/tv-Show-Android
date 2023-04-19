package com.eem.remotedata.api

import com.eem.remotedata.model.authentication.RemoteGuestToken

interface TestApiService {

    suspend fun getTestText(): String
    suspend fun getGuestToken(): RemoteGuestToken
}
