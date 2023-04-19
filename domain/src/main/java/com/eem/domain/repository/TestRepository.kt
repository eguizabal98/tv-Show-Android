package com.eem.domain.repository

import com.eem.domain.model.Test
import com.eem.domain.model.result.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface TestRepository {

    suspend fun getHtml(): Flow<ResultWrapper<Test>>
}
