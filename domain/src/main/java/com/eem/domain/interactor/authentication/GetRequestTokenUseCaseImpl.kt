package com.eem.domain.interactor.authentication

import com.eem.domain.model.authentication.RequestToken
import com.eem.domain.model.result.ResultWrapper
import com.eem.domain.repository.authentication.AuthenticationRepository
import javax.inject.Inject

class GetRequestTokenUseCaseImpl @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) : GetRequestTokenUseCase {

    override suspend fun invoke(): ResultWrapper<RequestToken> =
        authenticationRepository.getRequestToken()
}
