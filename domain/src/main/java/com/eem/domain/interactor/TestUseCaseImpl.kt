package com.eem.domain.interactor

import com.eem.domain.model.Test
import com.eem.domain.model.result.ResultWrapper
import com.eem.domain.repository.TestRepository
import kotlinx.coroutines.flow.Flow

class TestUseCaseImpl(
    private val testRepository: TestRepository
) : TestUseCase {

    override suspend fun invoke(): Flow<ResultWrapper<Test>> = testRepository.getHtml()
}
