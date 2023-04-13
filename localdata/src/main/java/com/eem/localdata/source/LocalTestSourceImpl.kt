package com.eem.localdata.source

import com.eem.data.datasource.local.LocalTestSource
import com.eem.localdata.database.TestDao
import com.eem.localdata.model.Test
import kotlin.random.Random

class LocalTestSourceImpl(
    private val testDao: TestDao,
) : LocalTestSource {

    override suspend fun getTest(): String? = testDao.getTest()?.testName

    override suspend fun insertTest(test: String) {
        clearTest()
        testDao.insertTest(
            Test(Random.nextInt(), test)
        )
    }

    override suspend fun clearTest() = testDao.clearTest()
}