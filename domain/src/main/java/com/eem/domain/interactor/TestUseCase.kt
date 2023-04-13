package com.eem.domain.interactor

import kotlinx.coroutines.flow.Flow

interface TestUseCase {

    suspend operator fun invoke(): Flow<String>
}
