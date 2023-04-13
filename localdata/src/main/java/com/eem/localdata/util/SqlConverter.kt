package com.eem.localdata.util

import com.eem.localdata.model.Test
import com.eem.sqldelight.Test_Entity

object SqlConverter {

    fun List<Test_Entity>.toDataList(): List<Test> = this.map {
        Test(
            testId = it.test_Id.toInt(),
            testName = it.test_name
        )
    }

    fun Test_Entity.toData(): Test = Test(
        testId = test_Id.toInt(),
        testName = test_name
    )
}