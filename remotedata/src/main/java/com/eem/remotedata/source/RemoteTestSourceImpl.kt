package com.eem.remotedata.source

import com.eem.data.datasource.remote.RemoteTestSource
import com.eem.remotedata.api.TestApiService

class RemoteTestSourceImpl(private val testApiService: TestApiService) : RemoteTestSource {

    override suspend fun testRemote(): String = testApiService.getTestText()
}