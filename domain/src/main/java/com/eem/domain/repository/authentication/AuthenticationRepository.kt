package com.eem.domain.repository.authentication

import com.eem.domain.model.authentication.GuestToken
import com.eem.domain.model.authentication.RequestToken
import com.eem.domain.model.authentication.SessionId
import com.eem.domain.model.authentication.SessionIdRequest
import com.eem.domain.model.result.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface AuthenticationRepository {

    suspend fun getGuestToken(): Flow<ResultWrapper<GuestToken>>
    suspend fun getRequestToken(): Flow<ResultWrapper<RequestToken>>
    suspend fun getSessionId(sessionIdRequest: SessionIdRequest): Flow<ResultWrapper<SessionId>>
}
