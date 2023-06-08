package com.eem.domain.interactor.authentication

import com.eem.domain.model.authentication.SessionId
import com.eem.domain.model.authentication.SessionIdRequest
import com.eem.domain.model.result.ResultWrapper
import com.eem.domain.repository.authentication.AuthenticationRepository
import javax.inject.Inject

class GetSessionIdUseCaseImpl @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) : GetSessionIdUseCase {

    override suspend fun invoke(sessionIdRequest: SessionIdRequest): ResultWrapper<SessionId> =
        authenticationRepository.getSessionId(sessionIdRequest)
}
