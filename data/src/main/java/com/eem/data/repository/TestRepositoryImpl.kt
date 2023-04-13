package com.eem.data.repository

import com.eem.data.datasource.local.LocalTestSource
import com.eem.data.datasource.remote.RemoteTestSource
import com.eem.domain.repository.TestRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class TestRepositoryImpl(
    private val remoteTestSource: RemoteTestSource,
    private val localTestSource: LocalTestSource,
) : TestRepository {

    override suspend fun getHtml(): Flow<String> = flow {
        val localData = localTestSource.getTest()
        if (localData.isNullOrEmpty().not()) {
            emit(localData.plus(" From Local"))
        }
        val remoteData = remoteTestSource.testRemote()
        localTestSource.insertTest(remoteData)
        emit(remoteData.plus(" From Remote"))
    }
}
