package com.eem.localdata.database

import com.eem.localdata.model.Test

interface TestDao {

    suspend fun getTest(): Test?
    suspend fun insertTest(test: Test)
    suspend fun clearTest()
}
