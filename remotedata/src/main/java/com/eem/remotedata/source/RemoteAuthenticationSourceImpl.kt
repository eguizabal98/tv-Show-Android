package com.eem.remotedata.source

import com.eem.data.datasource.remote.RemoteAuthenticationSource
import com.eem.data.model.authentication.DataGuestToken
import com.eem.data.model.authentication.DataRequestToken
import com.eem.data.model.base.ResponseWrapper
import com.eem.remotedata.api.AuthApiService
import com.eem.remotedata.base.BaseRemoteSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class RemoteAuthenticationSourceImpl(
    private val testApiService: AuthApiService,
    dispatcher: CoroutineDispatcher
) : RemoteAuthenticationSource, BaseRemoteSource(dispatcher) {

    override suspend fun getGuestToken(): Flow<ResponseWrapper<DataGuestToken>> = fetchData {
        testApiService.getGuestToken()
    }

    override suspend fun getRequestToken(): Flow<ResponseWrapper<DataRequestToken>> = fetchData {
        testApiService.getRequestToken()
    }
}
