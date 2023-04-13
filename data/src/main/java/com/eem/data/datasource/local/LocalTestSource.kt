package com.eem.data.datasource.local

interface LocalTestSource {

    suspend fun getTest(): String?
    suspend fun insertTest(test: String)
    suspend fun clearTest()
}