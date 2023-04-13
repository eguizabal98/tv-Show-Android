package com.eem.localdata.database

import com.eem.localdata.model.Test
import com.eem.localdata.util.SqlConverter.toData
import com.eem.sqldelight.TestEntityQueries

class TestDaoImpl(private val testEntityQueries: TestEntityQueries) : TestDao {

    override suspend fun getTest(): Test? = testEntityQueries.getTest().executeAsOneOrNull()?.toData()

    override suspend fun insertTest(test: Test) = testEntityQueries.insertTest(test.testId.toLong(), test.testName)

    override suspend fun clearTest() = testEntityQueries.clearTest()
}
