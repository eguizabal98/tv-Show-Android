package com.eem.data.datasource.remote

import com.eem.data.model.ResponseWrapper
import kotlinx.coroutines.flow.Flow

interface RemoteTestSource {

    suspend fun testRemote(): Flow<ResponseWrapper<String>>
}
