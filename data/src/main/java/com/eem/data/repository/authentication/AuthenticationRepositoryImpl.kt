package com.eem.data.repository.authentication

import com.eem.data.base.BaseRepository
import com.eem.data.datasource.remote.RemoteAuthenticationSource
import com.eem.data.model.authentication.DataSessionIdRequest
import com.eem.domain.model.authentication.GuestToken
import com.eem.domain.model.authentication.RequestToken
import com.eem.domain.model.authentication.SessionId
import com.eem.domain.model.authentication.SessionIdRequest
import com.eem.domain.model.result.ResultWrapper
import com.eem.domain.repository.authentication.AuthenticationRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class AuthenticationRepositoryImpl(
    private val remoteAuthenticationSource: RemoteAuthenticationSource,
    dispatcher: CoroutineDispatcher
) : AuthenticationRepository, BaseRepository(dispatcher) {

    override suspend fun getGuestToken(): Flow<ResultWrapper<GuestToken>> = onlyRemoteFetch {
        remoteAuthenticationSource.getGuestToken()
    }

    override suspend fun getRequestToken(): Flow<ResultWrapper<RequestToken>> = onlyRemoteFetch {
        remoteAuthenticationSource.getRequestToken()
    }

    override suspend fun getSessionId(sessionIdRequest: SessionIdRequest): Flow<ResultWrapper<SessionId>> =
        onlyRemoteFetch {
            remoteAuthenticationSource.getSessionId(
                DataSessionIdRequest(sessionIdRequest.requestToken)
            )
        }
}
