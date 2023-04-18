package com.eem.domain.interactor

import com.eem.domain.model.result.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface TestUseCase {

    suspend operator fun invoke(): Flow<ResultWrapper<String>>
}
