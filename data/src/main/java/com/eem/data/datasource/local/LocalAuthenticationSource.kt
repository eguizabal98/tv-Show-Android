package com.eem.data.datasource.local

import com.eem.data.model.authentication.DataSessionId

interface LocalAuthenticationSource {
    suspend fun getSessionId(): DataSessionId?
    suspend fun insertSessionId(sessionId: String)
}
