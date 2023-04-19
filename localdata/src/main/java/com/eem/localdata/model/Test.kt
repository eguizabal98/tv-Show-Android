package com.eem.localdata.model

import com.eem.data.model.base.DataMapper
import kotlinx.serialization.Serializable

@Serializable
data class Test(
    val testId: Int,
    val testName: String
) : DataMapper<String>() {
    override fun mapToDataModel(): String = testName
}
