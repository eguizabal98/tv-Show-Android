package com.eem.localdata.model

import kotlinx.serialization.Serializable

@Serializable
data class Test(
    val testId: Int,
    val testName: String
)
