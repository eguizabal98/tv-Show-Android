package com.eem.data.repository

import com.eem.data.base.BaseRepository
import com.eem.data.datasource.local.LocalTestSource
import com.eem.data.datasource.remote.RemoteTestSource
import com.eem.domain.model.result.ResultWrapper
import com.eem.domain.repository.TestRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class TestRepositoryImpl(
    private val remoteTestSource: RemoteTestSource,
    private val localTestSource: LocalTestSource,
    dispatcher: CoroutineDispatcher
) : TestRepository, BaseRepository(dispatcher) {

    override suspend fun getHtml(): Flow<ResultWrapper<String>> = localAndFetch(
        localSourceRequest = {
            localTestSource.getTest()
        },
        shouldFetch = {
            it.isNullOrEmpty()
        },
        remoteSourceRequest = {
            remoteTestSource.testRemote()
        },
        localSourceAction = {
            localTestSource.insertTest(it)
            localTestSource.getTest()
        }
    )
}
