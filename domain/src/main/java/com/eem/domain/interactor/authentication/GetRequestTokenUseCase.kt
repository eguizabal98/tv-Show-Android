package com.eem.domain.interactor.authentication

import com.eem.domain.model.authentication.RequestToken
import com.eem.domain.model.result.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface GetRequestTokenUseCase {

    suspend operator fun invoke(): Flow<ResultWrapper<RequestToken>>
}
