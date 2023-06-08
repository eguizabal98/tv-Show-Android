package com.eem.domain.repository.authentication

import com.eem.domain.model.authentication.GuestToken
import com.eem.domain.model.authentication.RequestToken
import com.eem.domain.model.authentication.SessionId
import com.eem.domain.model.authentication.SessionIdRequest
import com.eem.domain.model.result.ResultWrapper

interface AuthenticationRepository {

    suspend fun getGuestToken(): ResultWrapper<GuestToken>
    suspend fun getRequestToken(): ResultWrapper<RequestToken>
    suspend fun getSessionId(sessionIdRequest: SessionIdRequest): ResultWrapper<SessionId>
    suspend fun isLogged(): ResultWrapper<Boolean>
}
