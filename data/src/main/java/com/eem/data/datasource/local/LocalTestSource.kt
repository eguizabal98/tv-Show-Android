package com.eem.data.datasource.local

import com.eem.data.model.Test

interface LocalTestSource {

    suspend fun getTest(): Test?
    suspend fun insertTest(test: String)
    suspend fun clearTest()
}
