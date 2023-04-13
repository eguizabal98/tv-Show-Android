package com.eem.data.datasource.remote

interface RemoteTestSource {

    suspend fun testRemote(): String
}
