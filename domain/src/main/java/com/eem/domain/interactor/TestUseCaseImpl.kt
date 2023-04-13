package com.eem.domain.interactor

import com.eem.domain.repository.TestRepository
import kotlinx.coroutines.flow.Flow

class TestUseCaseImpl(
    private val testRepository: TestRepository
) : TestUseCase {

    override suspend fun invoke(): Flow<String> = testRepository.getHtml()
}
