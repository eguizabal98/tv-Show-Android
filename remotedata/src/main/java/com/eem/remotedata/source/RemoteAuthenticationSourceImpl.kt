package com.eem.remotedata.source

import com.eem.data.datasource.remote.RemoteAuthenticationSource
import com.eem.data.model.authentication.DataGuestToken
import com.eem.data.model.authentication.DataRequestToken
import com.eem.data.model.authentication.DataSessionId
import com.eem.data.model.authentication.DataSessionIdRequest
import com.eem.data.model.base.ResponseWrapper
import com.eem.remotedata.api.AuthApiService
import com.eem.remotedata.base.BaseRemoteSource
import com.eem.remotedata.model.authentication.RemoteSessionIdRequest
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class RemoteAuthenticationSourceImpl(
    private val authApiService: AuthApiService,
    dispatcher: CoroutineDispatcher
) : RemoteAuthenticationSource, BaseRemoteSource(dispatcher) {

    override suspend fun getGuestToken(): Flow<ResponseWrapper<DataGuestToken>> = fetchData {
        authApiService.getGuestToken()
    }

    override suspend fun getRequestToken(): Flow<ResponseWrapper<DataRequestToken>> = fetchData {
        authApiService.getRequestToken()
    }

    override suspend fun getSessionId(dataSessionIdRequest: DataSessionIdRequest): Flow<ResponseWrapper<DataSessionId>> =
        fetchData {
            authApiService.createSession(
                RemoteSessionIdRequest(dataSessionIdRequest.requestToken)
            )
        }
}
