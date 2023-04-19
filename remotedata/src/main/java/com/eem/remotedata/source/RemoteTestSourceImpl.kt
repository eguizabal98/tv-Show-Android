package com.eem.remotedata.source

import com.eem.data.datasource.remote.RemoteTestSource
import com.eem.data.model.Test
import com.eem.data.model.base.ResponseWrapper
import com.eem.remotedata.api.TestApiService
import com.eem.remotedata.base.BaseRemoteSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteTestSourceImpl(
    private val testApiService: TestApiService,
    dispatcher: CoroutineDispatcher
) : RemoteTestSource, BaseRemoteSource(dispatcher) {

    override suspend fun testRemote(): Flow<ResponseWrapper<Test>> = flow {
        ResponseWrapper.Success(Test(testApiService.getTestText()))
    }
}
