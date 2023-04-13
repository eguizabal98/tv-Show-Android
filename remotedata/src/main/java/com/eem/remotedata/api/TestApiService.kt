package com.eem.remotedata.api

interface TestApiService {

    suspend fun getTestText(): String
}