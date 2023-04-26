package com.eem.domain.interactor.authentication

import com.eem.domain.model.authentication.RequestToken
import com.eem.domain.model.result.ResultWrapper
import com.eem.domain.repository.authentication.AuthenticationRepository
import kotlinx.coroutines.flow.Flow

class GetRequestTokenUseCaseImpl(
    private val authenticationRepository: AuthenticationRepository
) : GetRequestTokenUseCase {

    override suspend fun invoke(): Flow<ResultWrapper<RequestToken>> =
        authenticationRepository.getRequestToken()
}
